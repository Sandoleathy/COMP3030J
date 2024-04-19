
<script setup>

import api from '../api'
import {onMounted} from 'vue'
import reservationPage from '../components/ReservationMenu.vue'
import reservationSearchBar from '../components/ReservationSearchBar.vue'
import roomItems from '../components/RoonItems.vue'
const clickTest = () => {
    api.get('/users')
        .then(response => {
            console.log(response.data);
        })
        .catch(error => {
            console.error(error);
        });
}

//页面初始化时会先执行的代码
onMounted(() => {
    clickTest();
    window.scrollTo(0, 0); // 添加这行代码来滚动到页面顶部
});
</script>

<template>
    <div class="navigation_bar" style="width: 100%;">
        <el-container class="container">
            <el-header class="header">
                <reservationPage></reservationPage>

            </el-header>

            <el-main class="main">
                <reservationSearchBar></reservationSearchBar>
                <roomItems></roomItems>
                <roomItems></roomItems>
                <roomItems></roomItems>
                <roomItems></roomItems>
                <roomItems></roomItems>
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