<template>
    <el-container class="container1">
        <el-header>
            <el-row justify="space-between">
                <el-col :span="20">
                    Wind Turbine
                </el-col>
                <el-col :span="4">
                    <span>Manage Device: </span>
                    <el-switch v-model="isStart"/>
                </el-col>
            </el-row>
        </el-header>
        <el-main>
            <el-row>
                <el-col :span="12">
                    Temperature: {{ temperature }}
                </el-col>
                <el-col :span="12">
                    Power Generation: {{ kwh }}
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    四小项
                </el-col>
                <el-col :span="12">
                    Reduce Carbon: {{ reduceCarbon }}
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</template>
<script setup>
import { ref } from 'vue'
import { ElContainer, ElMain, ElHeader, ElCol, ElRow } from 'element-plus'

const temperature = ref(0)
const kwh = ref(0)
const reduceCarbon = ref()

const U = ref(0)
const I = ref(0)
const W = ref(0)
const light = ref(0)

const isStart = ref(true)
</script>
<style scoped>
.container1{
    border: 1px solid black;
    padding: 0;
    border-radius: 5px;
    width: 100%;
    height: 400px;
    box-shadow: 2px 2px 5px #888888;
}
</style>