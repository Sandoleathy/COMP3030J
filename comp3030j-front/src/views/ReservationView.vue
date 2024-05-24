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

function handleSearch(buildingType) {
    console.log('Handling search for building type:', buildingType);
    const dbType = parseInt(typeMap[buildingType], 10);
    console.log('dbType:', dbType); // Check if dbType is correctly retrieved
// 从映射表获取数据库格式的buildingType

    if (dbType) {
        console.log('Filtering rooms for buildingType:', dbType);
        filteredRooms.value = rooms.value.filter(room => {
            console.log('Room buildingType:', room.hsRoom.buildingType); // Check what you're actually comparing
            return room.hsRoom.buildingType === dbType;
        });
    }
    else {
        filteredRooms.value = rooms.value; // 如果没有提供 buildingType 或不匹配，显示所有房间
        console.log('No valid buildingType provided, showing all rooms');
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