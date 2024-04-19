<script setup lang="ts">
import { ref, computed } from 'vue'
import type { Component } from 'vue';
import ReservationPage from '../components/ReservationMenu.vue'
import SideBar from '../components/MySidebar.vue'
import MyInformation from '../components/MyInformation.vue'
import OrderInformation from '../components/Order.vue'

const activeIndex = ref('3');

const currentComponent = computed((): Component | null => {
    switch (activeIndex.value) {
        case '1':
            return OrderInformation;
        case '3':
            return MyInformation;
        default:
            return null;
    }
});
</script>


<template>
    <div class="navigation_bar" style="width: 100%;">
        <el-container class="container">
            <el-header class="header">
                <ReservationPage />
            </el-header>
            <el-container>
                <el-aside class="aside" width="200px">
                    <SideBar @update:index="index => { console.log('Received index:', index); activeIndex.value = index }" />
                </el-aside>
                <el-main class="main">
                    <component :is="currentComponent" v-if="currentComponent" />
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>


<style scoped>
.container {
    position: absolute;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh; /* 设置为视窗的高度 */
    display: flex;
    flex-direction: column;
}
.aside{
    height: 100%;
}
.main{
    background: #f2f2f2;
}

@media (max-width: 1024px) {
    .container {
        min-width: 100vw;
    }
}

.header, .main, .aside {
    margin: 0;
    padding: 0;
}
.container {
    display: flex;
    flex: 1 1 auto; /* 允许容器伸展并填充可用空间 */
}
html, body {
    height: 100%;
    margin: 0;
    padding: 0;
}

</style>