<template>
    <div class="container">
        <el-container>
            <el-header class="header">
                <loginBar></loginBar>
            </el-header>
            <el-main class="main">
                <el-container>
                    <el-header>
                        <el-header>
                            <el-tabs v-model="pageNum">
                                <el-tab-pane label="Energy" name='0'></el-tab-pane>
                                <el-tab-pane label="Finance" name='1'></el-tab-pane>
                                <el-tab-pane label="Weather" name='2'></el-tab-pane>
                                <el-tab-pane label="Hotel" name='3'></el-tab-pane>
                                <el-tab-pane label="User" name='4'></el-tab-pane>
                            </el-tabs>
                        </el-header>
                    </el-header>
                    <el-main>
                        <div v-if="pageNum=='0'">
                            <windDetail></windDetail>
                            <el-divider></el-divider>
                            <solarDetail></solarDetail>
                            <el-divider></el-divider>
                        </div>
                        <div v-if="pageNum=='3'">
                            <hotelInfo></hotelInfo>
                            <el-divider></el-divider>
                        </div>
                    </el-main>
                </el-container>
            </el-main>
        </el-container>
    </div>
    
</template>

<script setup>
import { ElContainer, ElHeader, ElMain, ElMenu, ElMenuItem, ElRow, ElCol, ElDivider,ElTabs, ElTabPane } from 'element-plus';
import { Chart, registerables } from 'chart.js';
import loginBar from '../components/LoginBar.vue';
import windDetail from '../components/ParkInformationBlock/WindDetail.vue';
import solarDetail from '../components/ParkInformationBlock/SolarDetail.vue'
import hotelInfo from '../components/HotelManagement/HotelInfo.vue'
import { ref } from 'vue'

Chart.register(...registerables);
const pageNum = ref('0')

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
.main{
    max-width: 100%
}
</style>