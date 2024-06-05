<script setup>
import {Chart,registerables} from 'chart.js'
import axios from '@/axios'
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";

let chart1 = null
let chart2 = null
let isLoading = ref(false)

Chart.register(...registerables); //记得注册！教程里都没写！3.0版本以上的chart.js需要手动注册控制器！

const createChart = () => {
  isLoading.value = true
  const ctx1 = document.getElementById('totalPieChart');
  const ctx2 = document.getElementById('totalBarChart');

  const token = sessionStorage.getItem('token')
  axios.get('/api/statistics/energy/dataFlow', {
    headers: {
      'Authorization': 'Bearer ' + token
    }
  }).then((response) => {
    const data = response.data
    //console.log(data)
    const chart1Data = {
      type: 'pie',
      data: {
        labels: ['Wind Turbine', 'Solar Panel', 'Hydraulic Generator'],
        datasets: [
          {
            label: 'Carbon reduced(kg)',
            data: [
              data.energySystemDataFlow[0].carbonReductionWind,
              data.energySystemDataFlow[0].carbonReductionSolar,
              data.energySystemDataFlow[0].carbonReductionHydro
            ],
            borderWidth: 1
          },
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            display: false,
            grid: {
              display: false
            }
          },
          x: {
            display: false,
            grid: {
              display: false
            }
          }
        },
        plugins: {
          title: {
            display: true,
            text: 'Carbon emission reduction',
            font: {
              size: 16
            }
          }
        },
      },
    }
    const chart2Data = {
      type: 'bar',
      data: {
        labels: ['Wind Turbine', 'Solar Panel', 'Hydraulic Generator'],
        datasets: [
          {
            label: 'Carbon reduced(kg)',
            data: [
              data.energySystemDataFlow[0].powerWind,
              data.energySystemDataFlow[0].powerSolar,
              data.energySystemDataFlow[0].powerHydro
            ],
            borderWidth: 1
          },
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            display: true,
            grid: {
              display: false
            }
          },
          x: {
            display: true,
            grid: {
              display: false
            }
          }
        },
        plugins: {
          title: {
            display: true,
            text: 'Power generated',
            font: {
              size: 16
            }
          }
        },
      },
    }
    chart1 = new Chart(ctx1, chart1Data)
    chart2 = new Chart(ctx2, chart2Data)
    //console.log("chart init finish")
    isLoading.value = false
  }).catch(error => {
    isLoading.value = false
    ElMessage.error('error fetching data')
    console.log(error)
  })
}

onMounted(() => {
  createChart()
})
</script>

<template>
  <div class="container1" v-loading="isLoading">
    <el-row>
      <el-col :span="12">
        <canvas id="totalPieChart"></canvas>
      </el-col>
      <el-col :span="12">
        <canvas id="totalBarChart"></canvas>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.container1{
  width: 100%;
  border: 1px solid black;
  min-width: 0;
  padding-bottom: 20px;
  border-radius: 10px;
  margin-top: 10px;
}
#totalPieChart{
  max-height: 200px;
}
#totalBarChart{
  max-height: 200px;
}
</style>