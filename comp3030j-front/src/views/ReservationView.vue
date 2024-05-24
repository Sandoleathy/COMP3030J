<script setup>
import { ref, onMounted,watch } from 'vue';
import reservationPage from '../components/ReservationMenu.vue';
import reservationSearchBar from '../components/ReservationSearchBar.vue';
import roomItems from '../components/RoonItems.vue';
import axios from "axios";


const rooms = ref([]);
const filteredRooms = ref([]);// 储存房间数据的响应式变量

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

function handleSearch(dateRange, buildingType) {
    console.log('Handling search for date range:', dateRange, 'and building type:', buildingType);
    const startDate = dateRange[0];
    const endDate = dateRange[1];
    const dbType = parseInt(typeMap[buildingType], 10);

    if (startDate && endDate && !isNaN(dbType)) {
        fetchFilteredRooms(startDate, endDate, dbType);
    } else {
        console.error('Invalid date or building type provided, showing all rooms');
        filteredRooms.value = rooms.value;
    }
}


watch(filteredRooms, (newVal, oldVal) => {
    console.log('Rooms:', rooms.value);
    console.log('Filtered Rooms:', filteredRooms.value);
    console.log('Example room:', rooms.value[0].hsRoom);

}, { deep: true });

function fetchRooms() {
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

        })
        .catch(error => {
            console.error('Failed to fetch rooms:', error);
        });
}

async function fetchFilteredRooms(startDate, endDate, buildingType) {
    const token = sessionStorage.getItem("token");
    console.log('Using already fetched rooms:', rooms.value);

        const availableRooms = [];
        for (let room of rooms.value) {
            console.log('room:', room.hsRoom.id);
            // 确保 room.hsRoom 存在并且 room.hsRoom.buildingType 与 buildingType 匹配
            if (room.hsRoom && room.hsRoom.buildingType === buildingType) {
                const reservationsResponse = await axios.get(`/api/homestay/rr/queryByRoomId/${room.hsRoom.id}`, {
                    headers: { 'Authorization': 'Bearer ' + token }
                });
                console.log(`Checking reservations for room ID ${room.hsRoom.id}:`, reservationsResponse.data.data);
                if (Array.isArray(reservationsResponse.data.data)) {
                    const isBooked = reservationsResponse.data.data.some(reservation =>
                        new Date(reservation.checkinTime) <= new Date(endDate) &&
                        new Date(reservation.checkoutTime) >= new Date(startDate)
                    );
                    if (!isBooked) {
                        console.log(`Room ID ${room.id} is available`);
                        availableRooms.push(room);
                    }
                } else {
                    console.error(`Failed to fetch reservations or incorrect data type for room ID ${room.hsRoom.id}`);
                }
            } else {
                // 在这里处理 room.hsRoom 不存在的情况
                console.log(`Room or room.hsRoom not properly defined for room ID: ${room.id}`);
            }
        }

        filteredRooms.value = availableRooms;
        console.log('Available rooms after filtering by date and type:', filteredRooms.value);
}



</script>

<template>
    <div class="navigation_bar" style="width: 100%;">
        <el-container class="container">
            <el-header class="header">
                <reservationPage></reservationPage>
            </el-header>
            <el-main class="main">
                <reservationSearchBar @search="handleSearch"></reservationSearchBar>
                <roomItems v-for="room in filteredRooms" :key="room.id" :roomData="room.hsRoom"></roomItems>

            </el-main>
        </el-container>
    </div>
</template>

<style scoped>
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

</style>