<template>
    <!--园区信息展示-用户-->
    <container class="container">
        <el-container >
            <el-header style="padding: 0;">
                <loginBar></loginBar>
            </el-header>

            <el-menu  class="el-menu-demo fixed-top" mode="horizontal":ellipsis="false" :default-active="'2'">
                <el-menu-item index="0">
                                <img
                                    style="width: 90px;height: 60px"
                                    src="/images/Logo.jpg"
                                    alt="Element logo"
                                />
                    <div class="logo_word">
                        <el-row>
                            <h3 class="styled-heading">依山别墅</h3>
                        </el-row>
                        <el-row>
                            <h3 class="styled-heading">Mountain Villa</h3>
                        </el-row>
                    </div>

                </el-menu-item>
                <div class="flex-grow" />
                <el-menu-item index="1" @click="backToEntry">About Us</el-menu-item>
                <el-menu-item index="2" >Overview</el-menu-item>
                <el-menu-item index="3" @click="backToEntry">News & Notices</el-menu-item>
                <el-menu-item index="4" @click="backToEntry">Hotel</el-menu-item>
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
.container {
   top:0;
    left: 0;
    position: absolute;
    min-width: 100vw; /* 根据实际内容调整 */
}

.topBar{
    width: 100%;
    background-color: white;
    padding-top: 10px;
}
.logo_word .styled-heading {
    color: green;        /* 设置字体颜色为绿色 */
    font-weight: bold;
    padding-top: -20px;/* 加粗字体 */
    margin-top: -15px;       /* 上边距设置为0 */
    margin-bottom: -25px;  /* 下边距设置为2px，保持行间距小 */
}
.flex-grow {
    flex-grow: 1;
}
.model{
    margin: auto;
    margin-top: 10px;
    box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.5);
}
</style>