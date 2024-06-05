<template>
    <div v-loading="isLoading">
        <canvas id = "chart">
        </canvas>
    </div>
</template>
<script>
import {Chart,registerables} from 'chart.js'
import { planetChartData, GetEnergyData } from "@/chartData/EnergyOverview.ts"
import axios from "axios";

Chart.register(...registerables); //记得注册！教程里都没写！3.0版本以上的chart.js需要手动注册控制器！
Chart.defaults.color = '#FFFFFF';
Chart.defaults.backgroundColor = '#FFFFFF';
Chart.defaults.borderColor = '#FFFFFF';
export default {
    name: 'EnergyOverviewChart',
    data() {
        return {
          chart1: Chart,
          isLoading: false
        }
    },
    mounted(){
        GetEnergyData()
        const ctx = document.getElementById("chart")
        this.chart1 = new Chart(ctx, planetChartData)
        this.updateData()
    },
    methods: {
      updateData(){
        const token = sessionStorage.getItem('token')
        this.isLoading = true
        axios.get('/api/statistics/energy/dataFlow', {
          headers: {
            'Authorization': 'Bearer ' + token
          }
        }).then((response) => {
          //console.log(response.data)
          const data = response.data;
          const dataset = []
          dataset.push(data.energySystemDataFlow[0].carbonReductionWind)
          dataset.push(data.energySystemDataFlow[0].carbonReductionSolar)
          dataset.push(data.energySystemDataFlow[0].carbonReductionHydro)

          this.chart1.data.datasets.data = dataset
          this.isLoading = false
          this.chart1.update()
        }).catch(error => {
          console.log(error)
          this.isLoading = false
        })

      }
    }
}
</script>
<style scoped>
canvas{
    width: 100%;
}
</style>