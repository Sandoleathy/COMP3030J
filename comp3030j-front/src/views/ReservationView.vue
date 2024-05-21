<script setup>
import { ref, onMounted } from 'vue';
import reservationPage from '../components/ReservationMenu.vue';
import reservationSearchBar from '../components/ReservationSearchBar.vue';
import roomItems from '../components/RoonItems.vue';
import axios from "axios";


const rooms = ref([]); // 储存房间数据的响应式变量

onMounted(() => {
    fetchRooms();
    window.scrollTo(0, 0);
});

// 获取房间数据的函数
function fetchRooms() {
    const token = sessionStorage.getItem("token");
    axios.get('/api/homestay/room/list', {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => {
            // Debugging log to check the structure of the response
            console.log('Response data:', response.data.rows);

            // Ensure the response data structure contains 'rows'
            rooms.value = response.data.rows;
            rooms.value.forEach(room => console.log('Room data:', room.hsRoom));

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
                <reservationSearchBar></reservationSearchBar>
                <roomItems v-for="room in rooms" :key="room.id" :roomData="room.hsRoom"></roomItems>
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