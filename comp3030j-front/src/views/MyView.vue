<template>
    <div class="navigation_bar" style="width: 100%;">
        <el-container class="container">
            <el-header class="header">
                <ReservationPage />
            </el-header>
            <el-container>
                <el-aside class="aside" width="200px">
                    <SideBar :activeIndex="activeIndex" @update:index="updateIndex" />
                </el-aside>
                <el-main class="main">
                    <component :is="currentComponent" />
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue'
import type { Component } from 'vue';
import ReservationPage from '../components/ReservationMenu.vue'
import SideBar from '../components/MySidebar.vue'
import MyInformation from '../components/MyInformation.vue'
import OrderInformation from '../components/Order.vue'

const activeIndex = ref('3');

// Computed property to determine which component to render
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

const updateIndex = (index: string) => {
    activeIndex.value = index;
};
</script>



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
    width: 25%;
    height: 100%;
}
.main{
  background-image: url('/images/user-bg.png'); /* 设置背景图片 */
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