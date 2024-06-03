<template>
    <div style="width: 100%;">
        <el-row justify="space-between">
            <el-col :span="10" style="font-size: 30px;font-weight: bolder;">
                Wind Turbine
            </el-col>
            <el-col :span="4">
                <span>Manage Device: </span>
                <el-switch v-model="isStart"/>
            </el-col>
        </el-row>
    </div>
    <el-container direction="horizontal" class="container1">
        <el-main>
            <el-row>
              <div style="width: 100%;text-align: center;font-weight: bolder">Kwh and Carbon Reduce</div>
              <el-col :span="24">
                <canvas id="kwh-carbon-chart-wind"></canvas>
              </el-col>
            </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div  class="dataBlock">
                Electric current: {{ I }}A
              </div>
            </el-col>
            <el-col :span="6">
              <div  class="dataBlock">
                Voltage: {{ U }}V
              </div>
            </el-col>
            <el-col :span="6">
              <div  class="dataBlock">
                Wind speed: {{ windSpeed }}m/s
              </div>
            </el-col>
            <el-col :span="6">
              <div  class="dataBlock">
                Wind energy conversion efficiency: {{ efficiency }}%
              </div>
            </el-col>
          </el-row>
          <el-row>
            <div style="width: 100%;text-align: center;font-weight: bolder">Device Status</div>
            <el-col :span="24">
              <canvas id="rpm-torque-chart"></canvas>
            </el-col>
          </el-row>
        </el-main>
    </el-container>
        

    
</template>
<script setup>
import { ElContainer, ElMain, ElHeader, ElMessage, ElRow, ElCol, ElSwitch } from 'element-plus';
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router';
import {Chart,registerables} from 'chart.js'
import { windTurbineWattCarbon, windTurbineRPMTorque, energyDataSets} from '@/chartData/EnergyDetail.ts'
import axios from "axios";

Chart.register(...registerables); //记得注册！教程里都没写！3.0版本以上的chart.js需要手动注册控制器！

const router = useRouter();
const I = ref(0)
const U = ref(690)
const windSpeed = ref(0)
const efficiency = ref(0)
const isStart = ref(true)

let wattCarbonData = windTurbineWattCarbon
let RPMTorqueData = windTurbineRPMTorque

let chart1 = null
let chart2 = null

let intervalId
onMounted(() => {
  createChart()
  updateData()
  intervalId = setInterval(updateData , 5000)
})

const createChart = () => {
  const ctx1 = document.getElementById("kwh-carbon-chart-wind")
  const ctx2 = document.getElementById("rpm-torque-chart")

  chart1 = new Chart(ctx1, wattCarbonData)
  chart2 = new Chart(ctx2, RPMTorqueData)
}

const updateData = () => {
  I.value = Number(energyDataSets.currentWind).toFixed(2);
  windSpeed.value = Number(energyDataSets.windSpeed).toFixed(2);
  efficiency.value = Number(energyDataSets.windEnergyConversionEfficiency).toFixed(2);

  wattCarbonData = windTurbineWattCarbon
  RPMTorqueData = windTurbineRPMTorque

  //console.log(windTurbineWattCarbon)
  chart1.data = wattCarbonData.data
  chart2.data = RPMTorqueData.data
  //
  chart1.update()
  chart2.update()
}

onUnmounted(() => {
  // 在组件卸载时清理定时器
  clearInterval(intervalId);
})
</script>
<style scoped>
.container1{
    border: 1px solid black;
    border-radius: 5px;
    width: 100%;
    min-height: 450px;
    box-shadow: 2px 2px 5px #888888;
}
.dataBlock{
  border-radius: 5px;
  border: 1px solid black;
  text-align: center;
}
#kwh-carbon-chart-wind{
  max-height: 200px;
}
#rpm-torque-chart{
  max-height: 200px;
}
</style>