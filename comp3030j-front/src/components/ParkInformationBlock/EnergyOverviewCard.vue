<template>
    <div class='energy-card'>
        <el-container>
            <el-main>
                <el-row>
                    <el-col :span="12">
                        <table class="energy-data-table" border="1">
                            <thead>Park green energy overview</thead>
                            <tr>
                                <td>Wind Turbine</td>
                                <td>KWH</td>
                                <td>Carbon</td>
                            </tr>
                            <tr>
                                <td>Solar</td>
                                <td>KWH</td>
                                <td>Carbon</td>
                            </tr>
                            <tr>
                                <td>Water</td>
                                <td>KWH</td>
                                <td>Carbon</td>
                            </tr>
                            <tr>
                                <td>Total</td>
                                <td>KWH</td>
                                <td>Carbon</td>
                            </tr>
                        </table>
                    </el-col>
                    <el-col :span="12">
                        <energyChart class="energy-chart"></energyChart>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
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
    height: 30vh;
    max-height: 30vh;
}
</style>