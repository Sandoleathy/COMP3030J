<template>
    <!--园区信息展示-用户-->
    <container class="container">
        <el-container >
            <el-main>
                <div >
                    <EnergyOverviewChart class="energy-chart"></EnergyOverviewChart>
                </div>
            </el-main>
        </el-container>
        <el-container>
            <el-main class="weather">
                <!--111-->
                <WeatherOverview></WeatherOverview>
            </el-main>
            <el-main>

            </el-main>
        </el-container>
    </container>
    
</template>
<script setup>
import EnergyOverviewChart from "/src/components/Charts/EnergyOverviewChart.vue"
import WeatherOverview from "/src/components/WeatherOverview.vue"
import { ElContainer, ElMain } from 'element-plus';

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
</style>