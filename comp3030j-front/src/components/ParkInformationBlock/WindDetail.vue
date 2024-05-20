<template>
    <div style="width: 100%;">
        <el-row justify="space-between">
            <el-col :span="10">
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
                <canvas id="kwh-carbon-chart"></canvas>
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
                wind speed: {{ windSpeed }}m/s
              </div>
            </el-col>
            <el-col :span="6">
              <div  class="dataBlock">
                风能转换效率: {{ efficiency }}%
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
import { windTurbineWattCarbon } from '@/chartData/EnergyDetail.ts'
import { windTurbineRPMTorque } from '@/chartData/EnergyDetail.ts'
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

let intervalId
onMounted(() => {
  createChart()
  updateData()
  intervalId = setInterval(updateData , 5000)
})

const createChart = () => {
  const ctx1 = document.getElementById("kwh-carbon-chart")
  const ctx2 = document.getElementById("rpm-torque-chart")

  new Chart(ctx1, wattCarbonData)
  new Chart(ctx2, RPMTorqueData)
}

const updateData = () => {
  const token = sessionStorage.getItem("token");
  axios.get('/api/statistics/energy/dataFlow', {
    headers: {
      'Authorization': 'Bearer ' + token
    }
  }).then(res => {
    const data = res.data
    console.log(data)
    windSpeed.value = Number(data.wind.windSpeed).toFixed(2);
    const a = data.energySystemDataFlow[0].windEnergyConversionEfficiency * 100
    efficiency.value = Number(a).toFixed(2);
    I.value = Number(data.energySystemDataFlow[0].currentWind).toFixed(2);
  }).catch(error => {
    console.log(error)
  })

  wattCarbonData = windTurbineWattCarbon
  RPMTorqueData = windTurbineRPMTorque
}

onUnmounted(() => {
  // 在组件卸载时清理定时器
  clearInterval(intervalId);
})
</script>
<style scoped>
.container1{
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
</style>