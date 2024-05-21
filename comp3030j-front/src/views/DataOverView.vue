<template>
    <!--园区信息展示-用户-->
    <container class="container">
        <el-container >
            <el-header style="padding: 0;">
                <loginBar></loginBar>
            </el-header>

            <el-menu class="el-menu-demo fixed-top" mode="horizontal" :ellipsis="false">
                    <el-menu-item index="0">
                        <img
                            style="width: 90px; height: 90px"
                            src="/images/Logo.jpg"
                            alt="Element logo"
                        />
                        <div class="logo_word">
                            <el-row>
                                <h3 class="styled-heading" style="font-size: 40px;">Yishan</h3>
                            </el-row>
                            <el-row>
                                <h3 class="styled-heading" style="font-size: 25px;">Mountain Villa</h3>
                            </el-row>
                        </div>
                    </el-menu-item>
                    <div class="flex-grow" />
                    <el-menu-item class="menu-item" index="1" @click="backToEntry('aboutUs')">About Us</el-menu-item>
                    <el-menu-item class="menu-item" index="2" @click="backToEntry">Overview</el-menu-item>
                    <el-menu-item class="menu-item" index="3" @click="backToEntry('news')">News & Notices</el-menu-item>
                    <el-menu-item class="menu-item" index="4" @click="backToEntry('minsu')" style="margin-right: 36px;">Hotel</el-menu-item>
                </el-menu>

            <div class="model">
                <parkModel></parkModel>
            </div>
            
            <el-divider></el-divider>

            <el-main>   
                <el-row>
                    <el-col :span="14">
                        <temperatureCard></temperatureCard>
                    </el-col>
                    <el-col :span="10">
                        <WeatherOverview class="weather-chart"></WeatherOverview>
                    </el-col>
                </el-row>       
                <el-row>
                    <el-col :span="24">
                        <energyCard></energyCard>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </container>
    
</template>
<script setup>
import EnergyOverviewChart from "/src/components/Charts/EnergyOverviewChart.vue"
import WeatherOverview from "/src/components/WeatherOverview.vue"
import temperatureCard from "/src/components/Charts/TemperatureCard.vue"
import loginBar from '../components/LoginBar.vue'
import parkModel from '../components/ParkModel.vue'
import energyCard from '../components/ParkInformationBlock/EnergyOverviewCard.vue'
import { ElContainer, ElMain, ElHeader, ElCol, ElRow, ElIcon, ElDivider } from 'element-plus';
import { onMounted, ref } from "vue";
import { useRouter } from 'vue-router';

const router = useRouter();
const backToEntry =() => {
    router.push('/')
}

onMounted(() => {

})
</script>
<style scoped>
.fixed-top {
    position: absolute;
    top: 60px;
    left: 0;
    height: 120px;
    width: 100%; /* 确保导航栏宽度与视窗宽度一致 */
    /*z-index: 1000; 设置一个高的z-index确保导航栏在内容之上 */
    background-color: #fff; /* 设置背景颜色以确保内容不透明显示 */
}
.container {
   top:0;
    left: 0;
    position: absolute;
    min-width: 100vw; /* 根据实际内容调整 */
}

.flex-grow {
    flex-grow: 1;
}

.topBar{
    width: 100%;
    background-color: white;
    padding-top: 10px;
}
.logo_word .styled-heading {
    color: #7b996e;        /* 设置字体颜色为绿色 */
    font-size: 25px;
    font-weight: bold;
    padding-top: -20px; /* 加粗字体 */
    margin-left: 10px;
    margin-top: -10px; /* 上边距*/
    margin-bottom: -15px; /* 下边距*/
}
.menu-item {
    font-size: 18px;
    font-weight: bold;
    --el-menu-active-color: #7b996e;
    --el-menu-hover-text-color: #7b996e;
    --el-menu-hover-bg-color: #dce3d8;
}

.model{
    margin: auto;
    margin-top: 140px;
    box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.5);
}
</style>