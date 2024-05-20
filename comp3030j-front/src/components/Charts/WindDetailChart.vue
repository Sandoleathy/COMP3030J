<template>
    <div class="chart-container">
        <canvas id="chart"></canvas>
    </div>
</template>
<script setup>
import {Chart,registerables} from 'chart.js'
import {ref,onMounted} from 'vue'
import { windTurbineWattCarbon } from '../../chartData/EnergyDetail.ts'

Chart.register(...registerables); //记得注册！教程里都没写！3.0版本以上的chart.js需要手动注册控制器！

const turbineData = windTurbineWattCarbon

onMounted( () => {
    createChart()
})

const createChart = () => {
    const ctx = document.getElementById("chart")
    // Chart.defaults.color = '#FFFFFF';
    // Chart.defaults.backgroundColor = '#FFFFFF';
    // Chart.defaults.borderColor = '#FFFFFF';
    new Chart(ctx, turbineData)
}
</script>
<style scoped>

</style>