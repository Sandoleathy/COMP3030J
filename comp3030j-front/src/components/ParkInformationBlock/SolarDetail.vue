<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="12">
                    温度
                </el-col>
                <el-col :span="12">
                    千瓦时
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    四小项
                </el-col>
                <el-col :span="12">
                    碳排放
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</template>
<script setup>
import { ref } from 'vue'
import { ElContainer, ElMain, ElHeader, ElCol, ElRow } from 'element-plus'
</script>
<style scoped>

</style>