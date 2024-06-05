<template>
    <el-container style="height: 100vh; background-size: cover;" class="container">
        <el-header>
            <el-page-header
                class="back"

                @back="goBack"
            ></el-page-header>
        </el-header>

        <el-main>
            <el-row>
                <el-col :span="12">
                    <!-- line chart -->
                    <div class="chart-container">
                        <canvas ref="lineChart"></canvas>
                    </div>
                </el-col>
                <el-col :span="12">
                    <!-- pie chart -->
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
        this.createBarChart();
        this.createPieChart();
        this.createTemperatureChart();
        this.createAlarmChart();
        setInterval(() => {
            this.createBarChart(); // 更新条形图数据
            this.createPieChart(); // 重新创建line chart以更新数据
        }, 5000);
    },
    setup() {
        const router = useRouter(); // 获取 router 实例

        // 定义一个方法，用于处理返回首页的逻辑
        const goBack = () => {
            router.push({ name: 'entry' }); // 使用 router.push 导航到首页
        };

        return { goBack };
    },
    data() {
        return {
            sharedChartData: {
                hydroPower: 0,
                solarPower: 0,
                windPower: 0
            },
            lineChartInstance: null,
            pieChartInstance: null,
            // 其他图表实例
        };
    },
    methods: {
        generateRandomData(min, max, count) {
            return Array.from({ length: count }, () => Math.floor(Math.random() * (max - min + 1)) + min);
        },
        createBarChart() {
            if (this.lineChartInstance) {
                this.lineChartInstance.destroy();
                this.lineChartInstance = null;
            }
            const ctx = this.$refs.lineChart.getContext('2d');

            const dataValues = this.generateRandomData(100, 500, 3);
            this.sharedChartData = {
                hydroPower: dataValues[0],
                solarPower: dataValues[1],
                windPower: dataValues[2]
            };

            const barChartData = {
                labels: ['Hydro Power', 'Solar Power', 'Wind Power'],
                datasets: [{
                    label: 'Energy Production (MWh)',
                    data: dataValues,
                    backgroundColor: [
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)'
                    ],
                    borderColor: [
                        'rgb(75, 192, 192)',
                        'rgb(255, 206, 86)',
                        'rgb(255, 99, 132)'
                    ],
                    borderWidth: 1
                }]
            };
            const barChartOptions = {
                type: 'bar',
                data: barChartData,
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
            this.lineChartInstance = new Chart(ctx, barChartOptions);
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
            if (this.pieChartInstance) {
                this.pieChartInstance.destroy();
                this.pieChartInstance = null;
            }
            const ctx = this.$refs.pieChart.getContext('2d');
            const pieChartData = {
                labels: ['Hydro Power', 'Solar Power', 'Wind Power'],
                datasets: [{
                    data: [this.sharedChartData.hydroPower, this.sharedChartData.solarPower, this.sharedChartData.windPower],
                    backgroundColor: [
                        'rgb(75, 192, 192)',
                        'rgb(255, 206, 86)',
                        'rgb(255, 99, 132)'
                    ],
                    hoverOffset: 4
                }]
            };
            const pieChartOptions = {
                type: 'pie',
                data: pieChartData,
                options: {

                }
            };
            this.pieChartInstance = new Chart(ctx, pieChartOptions);
        },


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