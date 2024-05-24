<script lang="ts" setup>
import { ref, nextTick } from 'vue'
import router from "@/router";

function goToMyView() {
    router.push({ name: 'my' });
}

function goToMainView() {
    router.push({ name: 'entry' });
}
function goToWeatherView() {
    router.push({ name: 'weather' });
}

</script>

<template>
    <el-menu class="el-menu-demo fixed-top" mode="horizontal"
             :ellipsis="false">
        <el-menu-item index="0">
            <img
                style="width: 50px;height: 50px"
                src="/images/Logo.jpg"
                alt="Element logo"
            />
            <div class="flex-container">
                <h3 class="styled-heading" style="font-size:30px; margin-left: 10px;">Yishan</h3>
                <h3 class="styled-heading" style="margin-top: 10px; margin-left: 5px;">Mountain Villa</h3>
            </div>
        </el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="1" @click="goToMainView">Home</el-menu-item>
        <el-menu-item index="2">My Order</el-menu-item>
        <el-menu-item index="3" @click="goToWeatherView">Weather</el-menu-item>
        <el-menu-item index="4">
            <div class="demo-type">
                <el-avatar @click="goToMyView"> user </el-avatar>
            </div>
        </el-menu-item>

    </el-menu>
</template>

<style scoped>

.flex-grow {
    flex-grow: 1;
}
.fixed-top{
    padding: 0 50px;
    margin: 0;
    text-align: right;
    top: 0;
    position: fixed;
    left: 0;
    width: 100%; /* 确保导航栏宽度与视窗宽度一致 */
    z-index: 1000;
}

.demo-type {
    display: flex;
}
.demo-type > div {
    flex: 1;
    text-align: center;
}

.demo-type > div:not(:last-child) {
    border-right: 1px solid var(--el-border-color);
}

.flex-container {
  display: flex;
  align-items: center;  /* 垂直居中 */
}
.styled-heading {
    color: #7b996e;        /* 设置字体颜色为绿色 */
    font-weight: bold;
}
</style>