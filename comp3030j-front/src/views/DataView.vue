<template>
    <el-container style="height: 100vh; background-size: cover;" class="container">
        <el-header>
            <el-page-header
                class="back"
                content="Back to Home"
                @back="goBack"
            ></el-page-header>
        </el-header>

        <el-main>
            <el-row>
                <el-col :span="12">
                    <!-- 折线图 -->
                    <div class="chart-container">
                        <canvas ref="lineChart"></canvas>
                    </div>
                </el-col>
                <el-col :span="12">
                    <!-- 饼图 -->
                    <div class="chart-container">
                        <canvas ref="pieChart"></canvas>
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                <div class="chart-container">
                    <canvas ref="temperatureChart"></canvas>
                </div>
                </el-col>
                <el-col :span="12">
                    <div class="chart-container">
                        <canvas ref="alarmChart"></canvas>
                    </div>
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
import { ElContainer, ElHeader, ElMain, ElMenu, ElMenuItem, ElRow, ElCol } from 'element-plus';
import { Chart, registerables } from 'chart.js';
import {useRouter} from "vue-router";
Chart.register(...registerables);
Chart.defaults.color = "#fff";

export default {
    name: 'EnergyDashboard',
    components: {
        ElContainer,
        ElHeader,
        ElMain,
        ElMenu,
        ElMenuItem,
        ElRow,
        ElCol
    },
    mounted() {
        this.createLineChart();
        this.createPieChart();
        this.createTemperatureChart();
        this.createAlarmChart();
    },
    setup() {
        const router = useRouter(); // 获取 router 实例

        // 定义一个方法，用于处理返回首页的逻辑
        const goBack = () => {
            router.push({ name: 'entry' }); // 使用 router.push 导航到首页
        };

        return { goBack };
    },
    methods: {
        createLineChart() {
            const lineChartData = {
                labels: ['January', 'February', 'March', 'April'],
                datasets: [{
                    label: 'Hydro Power',
                    data: [65, 59, 80, 81],
                    fill: false,
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1
                }, {
                    label: 'Solar Power',
                    data: [28, 48, 40, 19],
                    fill: false,
                    borderColor: 'rgb(255, 205, 86)',
                    tension: 0.1
                }, {
                    label: 'Wind Power',
                    data: [12, 50, 25, 23],
                    fill: false,
                    borderColor: 'rgb(255, 99, 132)',
                    tension: 0.1
                }]
            };
            const lineChartOptions = {
                type: 'line',
                data: lineChartData,
                options: {}
            };
            new Chart(this.$refs.lineChart, lineChartOptions);
        },
        createAlarmChart() {
            const alarmData = {
                labels: ['2023-04-01', '2023-04-02', '2023-04-03', '2023-04-04', '2023-04-05', '2023-04-06', '2023-04-07'],
                datasets: [{
                    label: 'Daily Alarm Counts',
                    data: [0, 1, 3, 0, 2, 5, 4],
                    fill: false,
                    borderColor: 'rgb(255, 99, 132)',
                    tension: 0.4,
                    backgroundColor: 'rgb(255, 99, 132)'
                }]
            };
            const alarmChartOptions = {
                type: 'line',
                data: alarmData,
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    },
                    responsive: true,
                    maintainAspectRatio: false
                }
            };
            new Chart(this.$refs.alarmChart, alarmChartOptions);
        },
        createTemperatureChart() {
            const temperatureData = {
                labels: ['2023-04-01', '2023-04-02', '2023-04-03', '2023-04-04', '2023-04-05', '2023-04-06', '2023-04-07'],
                datasets: [{
                    label: 'Daily Average Temperature (°C)',
                    data: [13, 16, 15, 14, 17, 18, 20],
                    fill: false,
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.4
                }]
            };
            const temperatureChartOptions = {
                type: 'line',
                data: temperatureData,
                options: {
                    scales: {
                        y: {
                            beginAtZero: false
                        }
                    }
                }
            };
            new Chart(this.$refs.temperatureChart, temperatureChartOptions);
        },
        createPieChart() {
            const pieChartData = {
                labels: ['Hydro Power', 'Solar Power', 'Wind Power'],
                datasets: [{
                    data: [300, 50, 100],
                    backgroundColor: [
                        'rgb(75, 192, 192)',
                        'rgb(255, 205, 86)',
                        'rgb(255, 99, 132)'
                    ],
                    hoverOffset: 4
                }]
            };
            const pieChartOptions = {
                type: 'pie',
                data: pieChartData,
                options: {}
            };
            new Chart(this.$refs.pieChart, pieChartOptions);
        }

    }
};


</script>

<style scoped>
.container {
    top:0;
    left: 0;
    position: absolute;
    min-width: 100vw;
    width: 90%;/* 根据实际内容调整 */
    background: #2c3e50;
    /*background: url("/src/assets/logo.svg")*/
}
.back{
    color: white;
}
.chart-container {
    position: relative;
    margin: auto;
    height: 40vh;
    width: 100%;
}
</style>