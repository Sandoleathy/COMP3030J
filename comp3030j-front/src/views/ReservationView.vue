<script setup>
import { ref, onMounted,watch } from 'vue';
import reservationPage from '../components/ReservationMenu.vue';
import reservationSearchBar from '../components/ReservationSearchBar.vue';
import roomItems from '../components/RoonItems.vue';
import axios from "@/axios";
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
import { ElMessage } from 'element-plus';


const rooms = ref([]);
const filteredRooms = ref([]);
const selectedDateRange = ref([]);
const selectedBuildingType = ref(null);
const selectedGuestCount = ref(1);// 储存房间数据的响应式变量
const loading = ref(true)

onMounted(() => {
    fetchRooms();
    window.scrollTo(0, 0);
});

// Reservation.vue
const typeMap = {
    '1': '1',
    '2': '2',
    '3': '3',
    '4': '4'
};

function handleSearch(dateRange, buildingType,guestCount) {
    loading.value=true;
    console.log('Handling search with date range:', dateRange, 'building type:', buildingType, 'guest count:', guestCount);
    selectedDateRange.value = dateRange;
    selectedBuildingType.value = buildingType;
    selectedGuestCount.value = guestCount;
    const startDate = dateRange[0];
    const endDate = dateRange[1];
    const dbType = parseInt(typeMap[buildingType], 10);

    if (!startDate || !endDate) {
        console.error('Date range is required for searching');
        return;
    }
    loading.value=true;
    fetchFilteredRooms(startDate, endDate, dbType, guestCount);
}


watch(filteredRooms, (newVal, oldVal) => {
    console.log('Rooms:', rooms.value);
    console.log('Filtered Rooms:', filteredRooms.value);
    console.log('Example room:', rooms.value[0].hsRoom);

}, { deep: true });

function fetchRooms() {
    loading.value=true;
    const token = sessionStorage.getItem("token");
    axios.get('/api/homestay/room/list', {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => {
            rooms.value = response.data.rows;
            console.log('Initial room fetch done, rooms:', rooms.value);
            filteredRooms.value = response.data.rows;  // 初始时显示所有房间
            loading.value=false;
        })
        .catch(error => {
            console.error('Failed to fetch rooms:', error);
        });
}

async function fetchFilteredRooms(startDate, endDate, buildingType, guestCount) {
    loading.value=true;
    const token = sessionStorage.getItem("token");
    console.log('Using already fetched rooms:', rooms.value);

    const availableRooms = [];
    for (let room of rooms.value) {

        if (!room.hsRoom) {
            console.log(`Room or room.hsRoom not properly defined for room ID: ${room.id}`);
            continue;
        }

        console.log('room:', room.hsRoom.id);

        let isTypeMatch = false;
        let isGuestCountMatch = false;

        // 设置最大容纳人数基于 buildingType
        const maxGuests = room.hsRoom.buildingType === 3 ? 10 : 2;

        if (guestCount > 2) {


            // 如果人数大于2，只考虑 buildingType 为 3 的房间
            if(buildingType){
                if (buildingType !== 3) {
                    // 如果指定了buildingType并且不为3，直接跳过当前房间
                    continue;
                }
                if (buildingType === 3) {
                    // 如果指定了buildingType并且不为3，直接跳过当前房间
                    isTypeMatch = room.hsRoom.buildingType === 3; // 确保房间类型为3
                    isGuestCountMatch = maxGuests >= guestCount;
                }
            }else{
                isTypeMatch = room.hsRoom.buildingType === 3; // 确保房间类型为3
                isGuestCountMatch = maxGuests >= guestCount;
            }

        } else if (guestCount <= 2 ) {
            if(buildingType){
                isTypeMatch = room.hsRoom.buildingType === buildingType;
                isGuestCountMatch = true;
            }else{
                isTypeMatch = true;
                isGuestCountMatch = true;
            }
            // 如果人数小于等于2

        }

        if (!isTypeMatch || !isGuestCountMatch) continue;

        const reservationsResponse = await axios.get(`/api/homestay/rr/queryByRoomId/${room.hsRoom.id}`, {
            headers: { 'Authorization': 'Bearer ' + token }
        });

        if (Array.isArray(reservationsResponse.data.data)) {
            const isBooked = reservationsResponse.data.data.some(reservation =>
                new Date(reservation.checkinTime) <= new Date(endDate) &&
                new Date(reservation.checkoutTime) >= new Date(startDate)
            );
            if (!isBooked) {
                availableRooms.push(room);
            }
        } else {
            console.error(`Failed to fetch reservations or incorrect data type for room ID ${room.hsRoom.id}`);
        }

    }
    if(availableRooms.length === 0){
        ElMessage({
            message: '当前条件下无可用房间，请更改搜索条件再试。',
            type: 'warning',
            duration: 5000
        });
    }

    loading.value=false;
    filteredRooms.value = availableRooms;
    console.log('Available rooms after filtering by date, type, and guest count:', filteredRooms.value);
}








</script>

<template>
  <div id="background">
    <div class="navigation_bar" style="width: 100%;">
        <el-container class="container">
            <el-header class="header">
                <reservationPage></reservationPage>
            </el-header>
            <el-main class="main" >
                <reservationSearchBar @search="handleSearch" v-loading="false" element-loading-background="rgba(122, 122, 122, 0.2)"></reservationSearchBar>
                <div class="roomitem" v-loading="loading" style="min-height: 500px">
                    <roomItems v-for="room in filteredRooms" :key="room.id" :roomData="room.hsRoom" :dateRange="selectedDateRange"
                               :buildingType="selectedBuildingType" :guest-count="selectedGuestCount" v-loading="loading" element-loading-background="rgba(122, 122, 122, 0.2)"></roomItems>
                </div>
                <div class="el-backtop">
                    <el-backtop :right="30" :bottom="100" />
                </div>
            </el-main>
        </el-container>
    </div>
  </div>
</template>

<style scoped>
#background{
  background-color: #f0f0f0; /* 设置背景颜色 */
  background-image: url('/images/user-bg.png'); /* 设置背景图片 */
  background-size: cover; /* 图片尺寸适应 */
  background-repeat: no-repeat; /* 不重复平铺 */

  position: absolute; /* 设置绝对定位 */
  width: 100%;
  height: 100vh;
  z-index: -1;
}

.container {
    top:0;
    left: 0;
    position: absolute;
    min-width: 100vw; /* 根据实际内容调整 */
}
.main{
    background: #f2f2f2;
}

@media (max-width: 1024px) {
    .container {
        min-width: 100vw;
    }
}

.header, .main {
    margin: 0;
    padding: 0;
}

.el-backtop {
    z-index: 1000; /* 确保它在页面上的其他元素之上 */
    opacity: 0.7; /* 稍微透明显示 */
    transition: opacity 0.3s;
}

.el-backtop:hover {
    opacity: 1; /* 鼠标悬停时完全不透明 */
}
</style>
