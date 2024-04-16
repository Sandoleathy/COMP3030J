<template>
    <div class="page-container">
        <el-menu :default-active="activeIndex" class="el-menu-demo fixed-top" mode="horizontal"
                 :ellipsis="false">
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
            <el-menu-item index="1" @click="scrollToSection('aboutUs')">About Us</el-menu-item>
            <el-menu-item index="2" @click="goToViewPage">Overview</el-menu-item>
            <el-menu-item index="3" >新闻&通知</el-menu-item>
            <el-menu-item index="4" @click="scrollToSection('minsu')">民宿</el-menu-item>
        </el-menu>

        <div class="block text-center">
            <el-carousel height="400px">
                <!--                <el-carousel-item v-for="item in 3" :key="item">-->
                <!--                    <h3 class="small justify-center" text="2xl">{{ item }}</h3>-->
                <!--                </el-carousel-item>-->

                <el-carousel-item v-for="(imageUrl, index) in imageUrls" :key="index">
                    <img :src="imageUrl" alt="carousel image" style="width: 100%; height: 400px; object-fit: contain;">
                </el-carousel-item>
            </el-carousel>
        </div>

        <div class="aboutUs" ref="aboutUsSection">
            <h1 class="line"></h1>
            <h3 class="header">关于我们</h3>
            <h3 class="welcome">欢迎来到我们的园区！</h3>
            <h4 class="context">在这片静谧而充满活力的乡村，我们以实现经济、社会和生态的可持续发展为使命，致力于创造一个独特而温馨的生活空间。
                在这里，您将体验到乡村生活的魅力和舒适，尽情享受大自然的馈赠。我们的民宿将为您提供舒适的住宿，让您在宁静的乡间度过惬意的时光；医疗保健养老服务中心将为您提供贴心的健康照护和关爱；可持续发展能源中心将引领您走向绿色生活，体验清洁能源的魅力。
                我们欢迎您的到来，让我们一同探索这片美丽的土地，感受乡村的温暖与包容，共同创造一个更美好的未来！</h4>
            <h3 class="ending">期待您的光临！</h3>
        </div>
        <div class="minsu" ref="minsuSection">
            <h1 class="line"></h1>
            <h1 class="minsu-head">民宿</h1>
            <el-row gutter={50}>
                <el-col :span="12" class="image-column">
                    <img class="minsu-pic" src="/images/pic1.png" alt="民宿图片">
                </el-col>
                <el-col :span="12" class="text-column">
                    <h3>欢迎来到我们的民宿，这里是乡村生活的理想之地。我们的民宿坐落在郁郁葱葱的山林间，环境清幽，空气清新。房间简洁而舒适，每一处细节都充满了温馨和家的氛围。
                        在这里，您可以远离喧嚣，尽情享受宁静的时光。清晨，您将被晨曦唤醒，鸟鸣声和山谷中的清泉声将伴您迎接新的一天。白天，您可以漫步于田园间，感受大自然的鬼斧神工；夜晚，您可以仰望星空，与家人朋友共享美好时光。
                        我们的民宿不仅提供舒适的住宿，更为您提供了丰富的活动和体验。您可以参加农家采摘活动，品尝新鲜的农产品；或者参与手工艺品制作，体验传统工艺的魅力。无论您是寻找休闲放松还是体验农村生活，我们都将竭诚为您提供最好的服务，让您在这里留下美好的回忆。
                        欢迎您来到我们的民宿，让我们一同分享乡村生活的美好时光！
                    </h3>
                    <el-button type="primary" class="reservation-button" @click="goToAboutPage">Reservation</el-button>
                </el-col>
            </el-row>
        </div>
    </div>




</template>

<style scoped>
.page-container {
    width: 90%;
    margin: auto;
    padding-top: 60px;
}
.fixed-top {
    position: fixed;
    top: 50px;
    left: 0;
    width: 100%; /* 确保导航栏宽度与视窗宽度一致 */
    z-index: 1000; /* 设置一个高的z-index确保导航栏在内容之上 */
    background-color: #fff; /* 设置背景颜色以确保内容不透明显示 */
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
    height: 400px;
    object-fit: contain;
}

.flex-grow {
    flex-grow: 1;
}

.aboutUs, .minsu {
    scroll-margin-top: 150px; /* 根据固定头部的高度进行调整 */
}

.line {
    border: none; /* 移除默认边框 */
    height: 1.5px; /* 线的高度 */
    background-color: #000000; /* 线的颜色，这里使用灰色 */
    /* 上下外边距，可调整 */
    margin: 60px auto 40px;
    width: 50%; /* 宽度为容器的100% */
}

.header, .welcome, .context, .ending {
    text-align: center;
}

/* 特定样式，使 header 和 ending 加粗，并设置更大的外边距 */
.header, .ending {
    font-weight: bold; /* 加粗 */
    margin-top: 40px; /* 外边距上方 */
    margin-bottom: 40px; /* 外边距下方 */
}

/* welcome 和 context 的外边距 */
.welcome, .context {
    margin-top: 20px; /* 外边距上方 */
    margin-bottom: 20px; /* 外边距下方 */
}
.image-column{
    padding-right: 30px;
}
.text-column{
    padding-left: 30px;
}
.minsu-pic {
    width: 100%; /* 确保图片自适应容器宽度 */
    height: auto; /* 保持图片原始宽高比 */
}
.minsu-head{
    text-align: center;
    font-weight: bold; /* 加粗 */
    margin-top: 40px; /* 外边距上方 */
    margin-bottom: 40px; /* 外边距下方 */
}

.reservation-button {
    margin-top: 20px; /* 在按钮和文本之间添加一些间隔 */
    background-color: #409EFF; /* 蓝色背景 */
    border-color: #409EFF; /* 蓝色边框，确保和背景一致 */
}
</style>

<script lang="ts" setup>
import { ref, nextTick } from 'vue'

const activeIndex = ref('0')

const scrollToSection = (sectionId) => {
    nextTick(() => {
        const section = document.querySelector(`.${sectionId}`)
        if (section) {
            section.scrollIntoView({ behavior: 'smooth' })
        }
    })
}
const imageUrls = ref([
    '/images/pic1.png',
    '/images/pic2.png',
])

import { useRouter } from 'vue-router';

const router = useRouter();

function goToAboutPage() {
    router.push({ name: 'reservation' });
}
function goToViewPage() {
    router.push({ name: 'overview' });
}
</script>