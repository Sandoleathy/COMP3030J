<template>
    <div>
        <canvas id = "chart">
        </canvas>
    </div>
</template>
<script>
import {Chart,registerables} from 'chart.js'
import { planetChartData, GetEnergyData } from "../../chartData/EnergyOverview.ts"

Chart.register(...registerables); //记得注册！教程里都没写！3.0版本以上的chart.js需要手动注册控制器！
//Chart.defaults.color = "#fff";  //设置默认颜色
export default {
    name: 'EnergyOverviewChart',
    data() {
        return {

        }
    },
    mounted(){
        GetEnergyData()
        const ctx = document.getElementById("chart")
        new Chart(ctx, planetChartData)
    }
}
</script>
<style scoped>
canvas{
    width: 100%;
}
</style>