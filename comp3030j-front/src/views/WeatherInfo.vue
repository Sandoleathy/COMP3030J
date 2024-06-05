<template>
    <div class="weather-container" style="background-image: url('public/images/sky.jpeg');">
        <div class="small-container">
        <h2> {{ t('weatherInfo.current') }}</h2>
        <p>{{ t('weatherInfo.last') }} {{ updateTime }}</p>
        <p>{{ t('weatherInfo.total') }} {{ totalPrecip }} mm</p>
        <p>{{ t('weatherInfo.time') }} {{ latestPrecipTime }}</p>
        <p>{{ t('weatherInfo.amount') }} {{ latestPrecipAmount }} mm</p>
        <template v-if="warnings.length > 0">
            <div v-for="(warning, index) in warnings" :key="index">
                <p>{{ t('weatherInfo.source') }} {{ warning.source }}</p>
                <p>{{ t('weatherInfo.details') }} {{ warning.content }}</p>
            </div>
        </template>
        <template v-else>
            <p>{{ t('weatherInfo.no') }}</p>
        </template>
        <el-button type="primary" @click="refreshData">{{ t('weatherInfo.refresh') }}</el-button>
        </div>
    </div>
</template>
<script setup>
import axios from '@/axios';
import { onMounted, ref } from 'vue';
import { ElButton, ElMessage } from 'element-plus';
import { useI18n } from 'vue-i18n';
const { t } = useI18n();

const location = "117.13,40.29";
const key = "76cbdc094c49493b9a1c3f7f6d659ff4";

let updateTime = ref('');
let totalPrecip = ref(0);
let latestPrecipTime = ref('');
let latestPrecipAmount = ref(0);
let warnings = ref([]);

onMounted(() => {
    getCurrentRainState();
    getWarningInfo();
});

const refreshData = () => {
    getCurrentRainState();
    getWarningInfo();
    ElMessage.success("Data updated successfully");
};

const getCurrentRainState = () => {
    axios.get("https://devapi.qweather.com/v7/minutely/5m", {
        params: {
            location: location,
            key: key
        }
    }).then(response => {
        const data = response.data;
        updateTime.value = new Date(data.updateTime).toLocaleString();
        totalPrecip.value = data.minutely.reduce((total, item) => total + parseFloat(item.precip), 0);
        const latestPrecip = data.minutely[0];
        latestPrecipTime.value = new Date(latestPrecip.fxTime).toLocaleTimeString();
        latestPrecipAmount.value = parseFloat(latestPrecip.precip);
    }).catch(error => {
        console.error('Error fetching data:', error);
    });
};

const getWarningInfo = () => {
    axios.get("https://devapi.qweather.com/v7/warning/now", {
        params: {
            location: location,
            key: key
        }
    }).then(response => {
        const data = response.data;
        if (data.warning.length > 0) {
            warnings.value = data.warning.map(warning => ({
                source: warning.source,
                content: warning.text
            }));
        }
    }).catch(error => {
        console.error('Error fetching data:', error);
    });
};
</script>
<style scoped>
.weather-container {
    height: 100vh;
    width: 100vw;
    margin: 0;
    padding: 20px;
    border: none;

    background-color: rgba(255, 255, 255, 0.8);
    background-size: cover;
    background-position: center;
    box-shadow: none;
    font-family: Arial, sans-serif;
    position: fixed;
    top: 0;
    left: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
}
.small-container{
    background-color: rgba(255, 255, 255, 0.8);
    padding: 20px;
    border-radius: 10px;
    box-shadow: 10px 10px 20px 0px rgba(0, 0, 0, 0.5);
}
h2 {
    color: #333;
    font-weight: bold;
}
p {
    color: #666;
    margin: 5px 0;
}
</style>
