<template>
    <div>
        <canvas id = "chart">
        </canvas>
    </div>
</template>
<script>
import {Chart,registerables} from 'chart.js'
import { planetChartData, GetEnergyData } from "@/chartData/EnergyOverview.ts"

Chart.register(...registerables); //记得注册！教程里都没写！3.0版本以上的chart.js需要手动注册控制器！
Chart.defaults.color = '#FFFFFF';
Chart.defaults.backgroundColor = '#FFFFFF';
Chart.defaults.borderColor = '#FFFFFF';
export default {
    name: 'EnergyOverviewChart',
    data() {
        return {
          chart1: Chart
        }
    },
    mounted(){
        GetEnergyData()
        const ctx = document.getElementById("chart")
        this.chart1 = new Chart(ctx, planetChartData)
    },
    methods: {
      updateData(){

      }
    }
}
</script>
<style scoped>
canvas{
    width: 100%;
}
</style>