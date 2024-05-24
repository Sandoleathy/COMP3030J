<template>
  <div style="width: 100%;">
    <el-row justify="space-between">
      <el-col :span="10" style="font-size: 30px;font-weight: bolder;">
        Hydraulic Generator
      </el-col>
      <el-col :span="4">
        <span>Manage Device: </span>
        <el-switch v-model="isStart"/>
      </el-col>
    </el-row>
  </div>
    <el-container class="container1">
      <el-main>
        <el-row>
          <el-col :span="24">
            <canvas id="kwh-carbon-chart-water"></canvas>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="dataBlock">
              Electric current: {{ I }}A
            </div>
          </el-col>
          <el-col :span="8">
            <div  class="dataBlock">
              Voltage: {{ U }}V
            </div>
          </el-col>
          <el-col :span="8">
            <div  class="dataBlock">
              Water speed: {{ speed }}m/s
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6" class="extra-data">
            <div  class="dataBlock" id="extra">
              {{ rpm }}RPM
            </div>
          </el-col>
          <el-col :span="12">
            <canvas id="flow-rate-chart"></canvas>
          </el-col>
          <el-col :span="6">
            <canvas id="water-level-chart"></canvas>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
</template>
<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import {ElContainer, ElMain, ElCol, ElRow, ElSwitch} from 'element-plus'
import axios from 'axios'
import {Chart,registerables} from 'chart.js'
import { waterWattCarbon, waterLevel, waterFlowRate, energyDataSets } from '@/chartData/EnergyDetail.ts'

Chart.register(...registerables); //记得注册！教程里都没写！3.0版本以上的chart.js需要手动注册控制器！

const isStart = ref(true)
const U = ref(220)
const I = ref(0)
const speed = ref(0)
const rpm = ref(0)

let wattCarbonData = waterWattCarbon
let waterLevelData = waterLevel
let waterFlowRateData = waterFlowRate

let chart1 = null
let chart2 = null
let chart3 = null

let intervalId
onMounted(() => {
  createChart()
  updateData()
  intervalId = setInterval(updateData , 5000)
})

const createChart = () => {
  const ctx1 = document.getElementById("kwh-carbon-chart-water")
  const ctx2 = document.getElementById("water-level-chart")
  const ctx3 = document.getElementById("flow-rate-chart")

  chart1 = new Chart(ctx1, wattCarbonData)
  chart2 = new Chart(ctx2, waterLevelData)
  chart3 = new Chart(ctx3, waterFlowRateData)
}

const updateData = () => {
  I.value = Number(energyDataSets.currentHydro).toFixed(2);
  speed.value = Number(energyDataSets.flowSpeed).toFixed(2);
  rpm.value = Number(energyDataSets.turbineSpeed).toFixed(2);

  wattCarbonData = waterWattCarbon
  waterLevelData = waterLevel
  waterFlowRateData = waterFlowRate

  chart1.data = wattCarbonData.data
  chart2.data = waterLevelData.data
  chart3.data = waterFlowRateData.data

  chart1.update()
  chart2.update()
  chart3.update()
}

onUnmounted(() => {
  // 在组件卸载时清理定时器
  clearInterval(intervalId);
})
</script>
<style scoped>
.container1{
  border: 1px solid black;
  padding: 0;
  border-radius: 5px;
  width: 100%;
  min-height: 500px;
  box-shadow: 2px 2px 5px #888888;
}
.dataBlock{
  border-radius: 5px;
  border: 1px solid black;
  text-align: center;
}
.extra-data{
  padding-top: 20px;
}
#extra{
  height: 100%;
  text-align: center;
  margin: auto;
}
#kwh-carbon-chart-water{
  max-height: 200px;
}
#water-level-chart{
  min-height: 200px;
  max-height: 200px;
}
</style>