<script lang="ts" setup>
import { ref, nextTick } from 'vue'
import router from "@/router";

const activeIndex = ref('0')

function goToMyView() {
    router.push({ name: 'my' });
}

function goToMainView() {
    router.push({ name: 'entry' });
}

</script>

<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo fixed-top" mode="horizontal"
             :ellipsis="false">
        <el-menu-item index="0">
            <!--            <img-->
            <!--                style="width: 100px"-->
            <!--                src="/images/element-plus-logo.svg"-->
            <!--                alt="Element logo"-->
            <!--            />-->
            <div
                    style="width: 100px; height: 50px; background-color: yellow;"
                    alt="Yellow square"
            ></div>
        </el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="1" @click="goToMainView">首页</el-menu-item>
        <el-menu-item index="2">我的订单</el-menu-item>
        <el-menu-item index="3" >天气</el-menu-item>
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
</style>