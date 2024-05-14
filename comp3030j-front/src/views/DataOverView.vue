<template>
    <!--园区信息展示-用户-->
    <container class="container">
        <el-container >
            <el-header style="padding: 0;">
                <loginBar></loginBar>
            </el-header>
            <div class="topBar">
                <el-row justify="space-between">
                    <el-col :span="8">
                        <img
                            style="width: 90px;height: 60px"
                            src="/images/Logo.jpg"
                            alt="Element logo"
                        />
                    </el-col>
                    <el-col :span="6">Data Over view</el-col>
                    <el-col :span="10">
                        <span>{{ year }}/{{ month }}/{{ day }}  {{ hours }}:{{ minutes }}:{{ seconds }}</span>
                        <el-icon>
                            <!--Weather-->
                        </el-icon>
                    </el-col>
                </el-row>
                </div>
            <el-main>
                <el-row>
                    <el-col :span="24">
                        
                    </el-col>
                </el-row>                   
                <el-row>
                    <el-col :span="12">
                        <EnergyOverviewChart class="energy-chart"></EnergyOverviewChart>
                    </el-col>
                    <el-col :span="12">
                        <WeatherOverview></WeatherOverview>
                    </el-col>
                </el-row>        
            </el-main>
        </el-container>
    </container>
    
</template>
<script setup>
import EnergyOverviewChart from "/src/components/Charts/EnergyOverviewChart.vue"
import WeatherOverview from "/src/components/WeatherOverview.vue"
import loginBar from '../components/LoginBar.vue'
import parkModel from '../components/ParkModel.vue'
import { ElContainer, ElMain, ElHeader, ElCol, ElRow, ElIcon } from 'element-plus';
import { onMounted, ref } from "vue";

var currentDate = new Date();

var year = ref(0)
var month = ref(0)
var day = ref(0)
var hours = ref(0)
var minutes = ref(0)
var seconds = ref(0)

const updateTime = () => {
    currentDate = new Date();
    year.value = currentDate.getFullYear(); // 年份
    month.value = currentDate.getMonth() + 1; // 月份（注意月份从 0 开始，所以要加 1）
    day.value = currentDate.getDate(); // 当月的日期
    hours.value = currentDate.getHours(); // 小时
    minutes.value = currentDate.getMinutes(); // 分钟
    seconds.value = currentDate.getSeconds(); // 秒
}
onMounted(() => {
    setInterval(updateTime,1000)
})
</script>
<style scoped>
.container {
   top:0;
    left: 0;
    position: absolute;
    min-width: 100vw; /* 根据实际内容调整 */
}
.energy-chart{
    max-height: 30vh;
    
}
.weather{
    max-width: 60vw;
}
.topBar{
    width: 100%;
    background-color: white;
    padding-top: 10px;
}
</style>