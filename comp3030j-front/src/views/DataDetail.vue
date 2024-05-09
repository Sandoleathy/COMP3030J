<template>
    <div class="container">
        <el-container>
            <el-header class="header">
                <loginBar></loginBar>
            </el-header>
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
 html,body,#app{
        height:100%;
        margin: 0px;
        padding: 0px;
}
.container {
    min-width: 100vw;
    width: 90%;/* 根据实际内容调整 */
    background: white
}
.header{
    padding: 0px;
    margin: 0;
    width: 100%;
}
</style>