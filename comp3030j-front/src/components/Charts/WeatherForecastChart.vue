<template>
    <table>
        <tr>
            <th></th>
        </tr>
    </table>
    <canvas id="chart1"></canvas>
</template>
<script setup>
import {Chart,registerables} from 'chart.js'
import {ref,onMounted} from 'vue'
import axios from 'axios'
import {ElMessage} from 'element-plus'
import 'chartjs-plugin-datalabels';//图表插件

Chart.register(...registerables); //记得注册！教程里都没写！3.0版本以上的chart.js需要手动注册控制器！

const weatherData = ref("")
const dateArray = ref([])
const maxTempArray = ref([])
const minTempArray = ref([])

onMounted( () => {
    getWeatherData()
})


const createChart = () => {
    const ctx = document.getElementById("chart1")
    new Chart(ctx, weatherData.value)
}

let location = "117.13,40.29";
let key = "76cbdc094c49493b9a1c3f7f6d659ff4";
const getWeatherData = () => {
    axios.get("https://devapi.qweather.com/v7/weather/7d",{
        params: {
            location: location,
            key: key,
            lang: "en"
        }
    }).then(response => {
        const data = response.data
        dateArray.value = []
        maxTempArray.value = []
        minTempArray.value = []
        for(var i = 0; i < 7; i++){
            dateArray.value.push(data.daily[i].fxDate)
            maxTempArray.value.push(data.daily[i].tempMax)
            minTempArray.value.push(data.daily[i].tempMin)
        }
        console.log(dateArray.value)
        setWeatherData()
    }).catch(error => {
        ElMessage.error("Fetch weather information failed")
        console.error(error)
    })
}

const setWeatherData = () => {
    console.log(dateArray.value)
    console.log(maxTempArray.value)
    console.log(minTempArray.value)
    weatherData.value = {
        type: 'line',
        data: {
            labels: dateArray.value,
            datasets: [
                {
                    label: 'Max Temperature',
                    data: maxTempArray.value
                },
                {
                    label: 'Min Temperature',
                    data: minTempArray.value
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: '°C', // 在这里设置单位
                        font: {
                        size: 12,
                        weight: 'bold'
                        }
                    }
                }
            },
            
        }
    }
    createChart()
}
</script>