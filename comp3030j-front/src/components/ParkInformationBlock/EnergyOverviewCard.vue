<template>
    <div class='energy-card'>
        <el-row>
            <el-col :span="12">
                <div class="table-container">
                    <div class="corner top-left"></div>
                    <div class="corner top-right"></div>
                    <div class="corner bottom-left"></div>
                    <div class="corner bottom-right"></div>
                <table class="energy-data-table">
                    <caption>Park Green Energy Overview</caption>
                    <tr>
                        <td style="font-weight: bold; color:#36a2eb;">Wind Turbine</td>
                        <td>KWH</td>
                        <td>Carbon</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold; color:#ff6384;">Solar</td>
                        <td>KWH</td>
                        <td>Carbon</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold; color:#ff9f40;">Hydraulic</td>
                        <td>KWH</td>
                        <td>Carbon</td>
                    </tr>
                    <tr>
                        <td>Total</td>
                        <td>KWH</td>
                        <td>Carbon</td>
                    </tr>
                </table>
                </div>
            </el-col>
            <el-col :span="12">
                <energyChart class="energy-chart"></energyChart>
            </el-col>
        </el-row>
    </div>
</template>
<script setup>
import axios from 'axios'
import { ref, onMounted } from 'vue'
import energyChart from '../Charts/EnergyOverviewChart.vue'
</script>
<style scoped>
.energy-data-table{
    width: 100%;
    height:100%;
    text-align: center;
}
.energy-chart{
    height: 35vh;
    max-height: 35vh;
}
.table-container {
    border-style: solid;
    border-width: 1px;
    border-color: rgba(255, 255, 255, 0);;
    position: relative;
    background: rgba(0, 0, 0, 0); /* 背景颜色 */
    text-align: center;
    margin: 20px auto auto 80px;
}

table {
    color: white;
    width: 100%;
    border-collapse: collapse;
}
caption {
    text-align: center;
    padding: 20px;
    font-size: 20px;
    font-weight: bold;
    border-bottom: 2px solid #444;
}
th, td {
    padding: 15px;
    text-align: center;
}
tr:nth-child(odd) {
    background-color: rgba(255, 255, 255, 0);
}
tr:nth-child(even) {
    background-color: rgba(255, 255, 255, 0.2);
}
tr:hover {
    background-color: rgba(255, 255, 255, 0.3);
}

.table-container::before {
    content: '';
    position: absolute;
    top: -2px;
    right: -2px;
    bottom: -2px;
    left: -2px;
    background: radial-gradient(circle at 50% 50%, rgba(0, 0, 0, 0), rgba(225, 225, 225, 0.1) 90%);
    background-blend-mode: screen;
    pointer-events: none;
}

.corner {
    position: absolute;
    width: 20px;
    height: 20px;
    border: 3px solid white;
}
.corner.top-left {
    top: -5px;
    left: -5px;
    border-right: none;
    border-bottom: none;
}
.corner.top-right {
    top: -5px;
    right: -5px;
    border-left: none;
    border-bottom: none;
}
.corner.bottom-left {
    bottom: -5px;
    left: -5px;
    border-right: none;
    border-top: none;
}
.corner.bottom-right {
    bottom: -5px;
    right: -5px;
    border-left: none;
    border-top: none;
}
</style>