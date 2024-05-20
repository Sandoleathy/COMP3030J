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
                            <el-collapse v-model="activeNames" >
                                <el-collapse-item name="1" title="Wind Turbine">
                                    <windDetail></windDetail>
                                </el-collapse-item>
                                <el-collapse-item name="2" title="Solar Panel">
                                    <solarDetail></solarDetail>
                                </el-collapse-item>
                                <el-collapse-item name="3" title="Hydraulic Generator">
                                    <waterDetail></waterDetail>
                                </el-collapse-item>
                            </el-collapse>
                        </div>
                        <div v-if="pageNum=='2'">
                            <weatherInfo></weatherInfo>
                            <tempretureCard></tempretureCard>
                            <warningCard></warningCard>
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
import { ElContainer, ElHeader, ElMain, ElDivider,ElTabs, ElTabPane, ElCollapse, ElCollapseItem} from 'element-plus';
import loginBar from '../components/LoginBar.vue';
import windDetail from '../components/ParkInformationBlock/WindDetail.vue';
import solarDetail from '../components/ParkInformationBlock/SolarDetail.vue';
import weatherInfo from '../components/WeatherOverview.vue';
import waterDetail from '../components/ParkInformationBlock/WaterDetail.vue';
import tempretureCard from '../components/Charts/TemperatureCard.vue';
import warningCard from '../components/Charts/WarningCard.vue';
import hotelInfo from '../components/HotelManagement/HotelInfo.vue';

import { ref } from 'vue'

//Chart.register(...registerables);
const pageNum = ref('0')
const activeNames = ref(['1'])

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