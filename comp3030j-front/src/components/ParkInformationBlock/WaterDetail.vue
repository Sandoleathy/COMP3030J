<template>
    <el-container class="container1">
        <el-header>

        </el-header>
        <el-main>
            <el-row>
                <el-col :span="12">流量</el-col>
                <el-col :span="6">上游水位</el-col>
                <el-col :span="6">下游水位</el-col>
            </el-row>
            <el-row>
                <el-col :span="6">I</el-col>
                <el-col :span="6">U</el-col>
                <el-col :span="12">RPM</el-col>
            </el-row>
            <el-row>
                <el-col :span="12">W</el-col>
                <el-col :span="12">Carbon</el-col>
            </el-row>
        </el-main>
    </el-container>
</template>
<script setup>
import { ref } from 'vue'
import { ElContainer, ElMain, ElHeader, ElCol, ElRow } from 'element-plus'
import axios from 'axios'

</script>
<style scope>
.container1{
    border: 1px solid black;
    padding: 0;
    border-radius: 5px;
    width: 100%;
    height: 400px;
    box-shadow: 2px 2px 5px #888888;
}
</style>