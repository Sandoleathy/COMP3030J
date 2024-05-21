<template>
        <div style="width: 100%;">
            <el-row justify="space-between">
                <el-col :span="20">
                    Solar Panel
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
                <canvas id="kwh-carbon-chart-solar"></canvas>
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
                Device temperature: {{ temperature }}°C
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <canvas id="electric-light-chart"></canvas>
            </el-col>
          </el-row>
        </el-main>
    </el-container>
</template>
<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { ElContainer, ElMain, ElHeader, ElCol, ElRow } from 'element-plus'
import axios from "axios";
import {Chart,registerables} from 'chart.js'
import { solarWattCarbon, solarElectricLight, energyDataSets } from '@/chartData/EnergyDetail.ts'

Chart.register(...registerables); //记得注册！教程里都没写！3.0版本以上的chart.js需要手动注册控制器！

const temperature = ref(0)

const U = ref(12)
const I = ref(0)

const isStart = ref(true)
let wattCarbonData = solarWattCarbon
let electricLightData = solarElectricLight

let chart1 = null
let chart2 = null

let intervalId
onMounted(() => {
  createChart()
  updateData()
  intervalId = setInterval(updateData , 5000)
})

const createChart = () => {
  const ctx1 = document.getElementById("kwh-carbon-chart-solar")
  const ctx2 = document.getElementById("electric-light-chart")

  chart1 = new Chart(ctx1, wattCarbonData)
  chart2 = new Chart(ctx2, electricLightData)
}

const updateData = () => {

  temperature.value = Number(energyDataSets.deviceTemperature).toFixed(2);
  I.value = Number(energyDataSets.currentSolar).toFixed(2);

  wattCarbonData = solarWattCarbon
  electricLightData = solarElectricLight

  chart1.data = wattCarbonData.data
  chart2.data = electricLightData.data

  chart1.update()
  chart2.update()
}
onUnmounted(() => {
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
#kwh-carbon-chart-solar{
  max-height: 200px;
}
#electric-light-chart{
  max-height: 200px;
}
</style>