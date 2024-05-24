<template>
    <!--园区信息展示-用户-->
    <container class="container">
        <div class="background"><el-container >
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
                    <el-menu-item class="menu-item" index="2" @click="goToViewPage">Overview</el-menu-item>
                    <el-menu-item class="menu-item" index="3" @click="backToEntry('news')">News & Notices</el-menu-item>
                    <el-menu-item class="menu-item" index="4" @click="backToEntry('minsu')" style="margin-right: 36px;">Hotel</el-menu-item>
                </el-menu>

            <div class="model-block">
                <div class="model">
                    <parkModel></parkModel>
                </div>
                <div class="model-intro">
                  <p style="margin-top: 40px; margin-left: 10px; font-weight: bold; font-size: 30px;">⬅ 3D model</p>
                  <p style="margin-left: 40px; font-weight: bold; font-size: 20px;">of the park</p>
                  <p style="margin-top: 20px; margin-left: 40px; margin-right: 30px; font-size: 15px;">Roll the mouse wheel to adjust distance</p>
                  <p style="margin-top: 10px; margin-left: 40px; margin-right: 30px; font-size: 15px;">Drag the mouse to adjust the perspective</p>
                </div>
            </div>

            <el-main>
                <!--Weather-->
                <el-divider class="divider"></el-divider>
                <h1 class="block-head">Weather</h1>
                <div class="weather-block">
                    <el-row>
                      <WeatherOverview></WeatherOverview>
                    </el-row>
                    <el-row>
                      <temperatureCard></temperatureCard>
                    </el-row>
                </div>


                <!--Energy Consumption-->
                <el-divider class="divider"></el-divider>
                <h1 class="block-head">Energy Consumption</h1>
                <el-row>
                    <el-col :span="24">
                        <energyCard></energyCard>
                    </el-col>
                </el-row>
            </el-main>
        </el-container></div>
    </container>
</template>

<script setup>
import EnergyOverviewChart from "/src/components/Charts/EnergyOverviewChart.vue"
import WeatherOverview from "/src/components/UserWeatherOverview.vue"
import temperatureCard from "/src/components/Charts/UserTemperatureCard.vue"
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
const goToViewPage = () => {
  router.push({ name: 'overview' });
};

onMounted(() => {

})
</script>
<style scoped>
.background {
    background-image: url('/images/bg.png');
    background-attachment: scroll; /* Make the background image scroll with the content */
    background-position: top; /* Center the background image */
    background-repeat: repeat-y; /* Repeat the background image vertically */
    background-size: cover; /* Ensure the background covers the entire viewport */
}
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

.block-head{
    color: white;
    text-align: center;
    font-size: 30px;
    font-weight: bold; /* 加粗 */
    margin-top: 40px; /* 外边距上方 */
    margin-bottom: 40px; /* 外边距下方 */
}
.divider {
    width: 80%;
    margin-left: auto;
    margin-right: auto;
    margin-top: 80px;
}

.model-block{
    display: flex;
}
.model{
    margin-top: 180px;
    margin-right: 55px;
    margin-left: 200px;
    box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
}
.model-intro{
    margin-top: 280px;
    width: 250px;
    height: 300px;
    position: relative;
    background-color: rgba(225, 225, 225, 0.75);
}

.weather-block{
    width: 60%;
    margin-left: auto;
    margin-right: auto;
}
</style>