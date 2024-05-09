<template>
    <div class="container">
        <loginBar></loginBar>
        <el-container>
            <el-header></el-header>
            <el-main>
                <windDetail></windDetail>
            </el-main>
        </el-container>
    </div>
    
</template>

<script setup>
import { ElContainer, ElHeader, ElMain, ElMenu, ElMenuItem, ElRow, ElCol } from 'element-plus';
import { Chart, registerables } from 'chart.js';
import loginBar from '../components/LoginBar.vue';
import windDetail from '../components/ParkInformationBlock/WindDetail.vue';

Chart.register(...registerables);




</script>

<style scoped>
.container {
    min-width: 100vw;
    width: 90%;/* 根据实际内容调整 */
    background: white
}
</style>