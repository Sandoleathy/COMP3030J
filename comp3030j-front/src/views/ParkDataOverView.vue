<template>
    <!--园区信息展示-用户-->
    <el-container class="container">
        <el-main>
            <EnergyOverviewChart></EnergyOverviewChart>
        </el-main>
    </el-container>
</template>
<script setup>
import EnergyOverviewChart from "/src/components/EnergyOverviewChart.vue"

</script>
<style scoped>
.container {
   top:0;
    left: 0;
    position: absolute;
    min-width: 100vw; /* 根据实际内容调整 */
}
</style>