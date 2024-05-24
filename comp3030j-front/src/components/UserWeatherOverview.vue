<template>
    <el-container>
        <el-main>
            <div class="card">
                <WeatherForcastChart class="weather"></WeatherForcastChart>
            </div>
        </el-main>
    </el-container>
</template>
<script setup>
import { ref , onMounted } from 'vue';
import { ElContainer, ElMain, ElHeader , ElMessage } from 'element-plus';
import axios from 'axios';
import WeatherForcastChart from './Charts/WeatherForecastChart.vue'

const temperature = ref(0)
const feelTemp = ref(0)
const weatherText = ref("")

let location = "117.13,40.29";
let key = "76cbdc094c49493b9a1c3f7f6d659ff4";
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
    }).catch(error => {
        ElMessage.error("Fetch weather information failed")
        console.error(error)
    })
}
</script>
<style scoped>
.weather{
    height: 40vh;
    max-height: 30vh;
}
.card{
    border-radius: 15px;
    border-style: solid;
    border-width: 1px;
    border-color: white;
    background: linear-gradient(to top, rgba(0, 0, 0, 0), rgba(225, 225, 225, 0.3));
    padding: 10px;
    text-align: center;
    margin: auto;
    box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.5);
}
</style>