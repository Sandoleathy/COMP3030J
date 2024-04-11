<template>
    <div class="page-container">
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        :ellipsis="false"
        @select="handleSelect"
    >
        <el-menu-item index="0">
<!--            <img-->
<!--                style="width: 100px"-->
<!--                src="/images/element-plus-logo.svg"-->
<!--                alt="Element logo"-->
<!--            />-->
            <div
                style="width: 100px; height: 100px; background-color: yellow;"
                alt="Yellow square"
            ></div>
        </el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="1">About Us</el-menu-item>
        <el-menu-item index="2">Overview
        </el-menu-item>
        <el-menu-item index="3">新闻&通知</el-menu-item>
        <el-menu-item index="4">民宿</el-menu-item>
    </el-menu>

        <div class="block text-center">
            <el-carousel height="750px">
<!--                <el-carousel-item v-for="item in 3" :key="item">-->
<!--                    <h3 class="small justify-center" text="2xl">{{ item }}</h3>-->
<!--                </el-carousel-item>-->

                    <el-carousel-item v-for="(imageUrl, index) in imageUrls" :key="index">
                        <img :src="imageUrl" alt="carousel image" style="width: 100%; height: 750px; object-fit: cover;">
                    </el-carousel-item>
            </el-carousel>
        </div>
  </div>




</template>

<style scoped>
.page-container {
    width: 100%;
}

.demonstration {
    color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}
.el-carousel__item img {
    width: 100%;
    height: 750px;
    object-fit: cover;
}

.flex-grow {
    flex-grow: 1;
}
</style>

<script lang="ts" setup>
import { ref } from 'vue'

const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}
const imageUrls = ref([
    '/images/pic1.png',
    '/images/pic2.png',
])
</script>

