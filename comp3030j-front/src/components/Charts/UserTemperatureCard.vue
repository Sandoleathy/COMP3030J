<template>
    <el-container>
    <el-main>
    <div class="temp-card">
        
        <el-row style="color: white;">
            <el-col :span="24" style="text-align: left;">
                <span style="font-weight: bold;font-size: 20px;"> Weather Forecast </span> | Update Time: {{ updateTime }}
            </el-col>
        </el-row>
        <el-row justify="space-between">
            <el-col :span="5">
                <table style="color:white">
                    <tr>
                        <td rowspan="2">
                            <i :class="icon" style="font-size: 60px;"></i>
                        </td>
                        <td style="font-size: 30px; vertical-align: bottom;">{{ temperature }}°C</td>
                    </tr>
                    <tr>
                        <td style="vertical-align:top;font-size:20px">{{ weatherText }}</td>
                    </tr>
                </table>
            </el-col>
            <el-col :span="8">
                <table style="color:white;text-align: left;">
                    <tr>
                        <td rowspan="2">
                            <arrow :degree="windVaneDegree"></arrow>
                        </td>
                        <td style="vertical-align: bottom;font-size: 18px;">
                            {{ windDirection }}
                        </td>
                    </tr>
                    <tr>
                        <td style="vertical-align: top;font-size: 18px;">
                            {{ windSpeed }} KM/H
                        </td>
                    </tr>
                </table>
            </el-col>
            <el-col :span="11" style="color: white;">
                <el-row>
                    <el-col :span="12">
                        Feel Temperature: {{ feelTemp }} °C
                    </el-col>
                    <el-col :span="12">
                        Humidity: {{ humidity }} %
                    </el-col>
                </el-row>
                <el-divider></el-divider>
                <el-row>
                    <el-col>
                        Atmospheric pressure: {{ pressure }} KPa
                    </el-col>
                    <el-col>

                    </el-col>
                </el-row>
            </el-col>
        </el-row>

    </div>
    </el-main>
    </el-container>
</template>
<script setup>
import axios from '@/axios'
import { ref, onMounted } from 'vue'
import { ElMessage, ElRow, ElCol, ElDivider } from 'element-plus';
import arrow from '../Arrow.vue'

let location = "117.13,40.29";
let key = "76cbdc094c49493b9a1c3f7f6d659ff4";

const updateTime = ref("")
const temperature = ref(0)
const feelTemp = ref(0)
const weatherText = ref("")
const windVaneDegree = ref(0)
const windDirection = ref("")
const windSpeed = ref(0)
const pressure = ref(0)
const humidity = ref(0)

const icon = ref('')
onMounted( () => {
    getCurrentWeatherInfo()
})
const getCurrentWeatherInfo = () => {
    //当日天气信息
    axios.get("https://devapi.qweather.com/v7/weather/now", {
        params: {
            location: location,
            key: key,
            lang: "en"
        }
    }).then(response => {
        const data = response.data
        temperature.value = data.now.temp
        feelTemp.value = data.now.feelsLike
        weatherText.value = data.now.text
        windVaneDegree.value = data.now.wind360
        windDirection.value = data.now.windDir
        windSpeed.value = data.now.windSpeed
        updateTime.value = data.now.obsTime
        pressure.value = data.now.pressure
        humidity.value = data.now.humidity

        icon.value = 'qi-' + data.now.icon + '-fill'
    }).catch(error => {
        ElMessage.error("Fetch weather information failed")
        console.error(error)
    })
}
</script>

<style scoped>
.temp-card {
    border-radius: 15px;
    border-style: solid;
    border-width: 1px;
    border-color: white;
    position: relative;
    background: rgba(0, 0, 0, 0); /* 背景颜色 */
    padding: 10px;
    text-align: center;
    margin: auto;
    box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.5);
}
.temp-card::before {
    border-radius: 15px;
    content: '';
    position: absolute;
    top: -2px;
    right: -2px;
    bottom: -2px;
    left: -2px;
    background: linear-gradient(to top, rgba(0, 0, 0, 0), rgba(225, 225, 225, 0.3));
    background-blend-mode: screen;
    pointer-events: none;
}
</style>