<script lang="ts" setup>
import { ref, nextTick } from 'vue'
import router from "@/router";

function goToMyView() {
    router.push({ name: 'my' });
}

function goToMainView() {
    router.push({ name: 'entry' });
}

</script>

<template>
    <el-menu class="el-menu-demo fixed-top" mode="horizontal"
             :ellipsis="false">
        <el-menu-item index="0">
            <img
                style="width: 90px;height: 60px"
                src="/images/Logo.jpg"
                alt="Element logo"
            />
            <div class="logo_word">
                <el-row>
                    <h3 class="styled-heading">依山别墅</h3>
                </el-row>
                <el-row>
                    <h3 class="styled-heading">Mountain Villa</h3>
                </el-row>
            </div>
        </el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="1" @click="goToMainView">Home</el-menu-item>
        <el-menu-item index="2">My Order</el-menu-item>
        <el-menu-item index="3" >Weather</el-menu-item>
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
    padding: 0px 100px;
    margin: 0;
    text-align: right;
    top: 0;
//position: absolute;
//width: 90vw;
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
.logo_word .styled-heading {
    color: green;        /* 设置字体颜色为绿色 */
    font-weight: bold;
    padding-top: -20px;/* 加粗字体 */
    margin-top: -15px;       /* 上边距设置为0 */
    margin-bottom: -25px;  /* 下边距设置为2px，保持行间距小 */
}
</style>