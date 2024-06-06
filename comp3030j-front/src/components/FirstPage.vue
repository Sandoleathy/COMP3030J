<template>
    <div class="background"><el-container style="margin-top: -80px;">
        <el-header class="header">
            <el-menu class="fixed-top" mode="horizontal" :ellipsis="false">
                <el-menu-item index="0">
                    <img
                        style="width: 90px; height: 90px"
                        src="/images/Logo.jpg"
                        alt="Element logo"
                    />
                    <div class="logo_word">
                        <el-row>
                            <h3 class="styled-heading" style="font-size: 40px;">{{ t('firstPage.yishan') }}</h3>
                        </el-row>
                        <el-row>
                            <h3 class="styled-heading" style="font-size: 25px;">{{ t('firstPage.mountainVilla') }}</h3>
                        </el-row>
                    </div>
                </el-menu-item>
                <div class="flex-grow" />
                <el-menu-item class="menu-item" index="1" @click="scrollToSection('aboutUs')">{{ t('firstPage.aboutUs') }}</el-menu-item>
                <el-menu-item class="menu-item" index="2" @click="goToViewPage">{{ t('firstPage.overview') }}</el-menu-item>
                <el-menu-item class="menu-item" index="3" @click="scrollToSection('news')">{{ t('firstPage.news') }}</el-menu-item>
                <el-menu-item class="menu-item" index="4" @click="scrollToSection('minsu')" style="margin-right: 20px;">{{ t('firstPage.hotel') }}</el-menu-item>
            </el-menu>
        </el-header>
    </el-container>

    <div class="block" style="position: relative; top: 60px; width: 100%">
        <el-carousel height="400px" width="100%">
            <el-carousel-item v-for="(imageUrl, index) in imageUrls" :key="index">
                <img :src="imageUrl" alt="carousel image" style="width: 100%; height: 400px; object-fit: contain;">
            </el-carousel-item>
        </el-carousel>
    </div>
        
    <div style="width: 80%; margin: auto;">
        <div class="aboutUs" ref="aboutUsSection">
            <h1 class="line1"></h1>
            <h1 class="header">{{ t('firstPage.aboutUs') }}</h1>
            <h3 class="welcome">{{ t('firstPage.welcome') }}</h3>
            <h4 class="context">{{ t('firstPage.text1') }}</h4>
            <h3 class="ending" style="margin-bottom: 50px">{{ t('firstPage.look') }}</h3>
        </div>
        <!--news-->
        <div class="news" ref="newsSection">
          <el-divider></el-divider>
          <newsCard></newsCard>
        </div>

        <div class="minsu" ref="minsuSection">
            <h1 class="line1"></h1>
            <h1 class="minsu-head">{{ t('firstPage.hotel') }}</h1>
            <h3 class="minsu-head">{{ t('firstPage.living') }}</h3>
            <el-row gutter={50}>
                <el-col :span="12" class="image-column">
                    <img class="minsu-pic" src="/images/hotel.png" alt="hotel-pic">
                </el-col>
                <el-col :span="12" class="text-column">
                    <div class="text-content">
                        <p>{{ t('firstPage.our') }}
                        <br><br>{{ t('firstPage.also') }}
                        <br><br> {{ t('firstPage.weather') }}
                        </p>
                        <el-button type="primary" class="reservation-button" @click="goToAboutPage">{{ t('firstPage.reservation') }}</el-button>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div></div>
</template>

<style scoped>
.background {
    background-image: url('/images/bg.png');
    background-attachment: scroll; /* Make the background image scroll with the content */
    background-position: top; /* Center the background image */
    background-repeat: repeat-y; /* Repeat the background image vertically */
    background-size: cover; /* Ensure the background covers the entire viewport */
}

.fixed-top {
    display: flex;
    position: absolute;
    top: 60px;
    left: 0;
    height: 120px;
    width: 100%; /* 确保导航栏宽度与视窗宽度一致 */
    /*z-index: 1000; 设置一个高的z-index确保导航栏在内容之上 */
    background-color: #fff; /* 设置背景颜色以确保内容不透明显示 */
}

.el-carousel__item:nth-child(2n) {
    background-color: rgba(211, 220, 230, 0.25);
}
.el-carousel__item:nth-child(2n + 1) {
    background-color: rgba(211, 220, 230, 0.25);
}

.el-carousel__item img {
    width: 100%;
    height: 400px;
    object-fit: contain;
}

.flex-grow {
    flex-grow: 1;
}

.aboutUs, .minsu,.news {
    scroll-margin-top: 150px; /* 根据固定头部的高度进行调整 */
}

.line1 {
    border: none; /* 移除默认边框 */
    height: 1.5px; /* 线的高度 */
    opacity: 1;
    /* 上下外边距，可调整 */
    margin: 60px auto 40px;
    width: 50%; /* 宽度为容器的100% */
}

.header, .welcome, .context, .ending {
    text-align: center;
}

/* 特定样式，使 header 和 ending 加粗，并设置更大的外边距 */
.header, .ending {
    color: white;
    font-weight: bold; /* 加粗 */
    margin-top: 40px; /* 外边距上方 */
    margin-bottom: 40px; /* 外边距下方 */
}

/* welcome 和 context 的外边距 */
.welcome, .context {
    color: white;
    margin-top: 20px; /* 外边距上方 */
    margin-bottom: 20px; /* 外边距下方 */
}
.image-column {
    display: flex;
    justify-content: center;
    align-items: center; /* 图片水平和垂直居中 */
}

.text-column {
    display: flex;
    flex-direction: column;
    justify-content: center;
    text-align: justify; /* 文本两端对齐 */
    padding: 20px; /* 添加一些内边距 */
}

.text-content {
    position: relative;
    max-width: 100%; /* 控制文本块的最大宽度 */
}

.reservation-button {
    margin-top: 20px; /* 预订按钮与文本间距 */
    float: right; /* 将按钮对齐到右侧 */
    font-size: 20px;
    font-weight: bold;
    height: 40px;
    --el-button-bg-color: #7b996e;
    --el-button-border-color: #7b996e;
    --el-button-hover-bg-color: #dce3d8;
    --el-button-hover-border-color: #dce3d8;
    --el-button-active-bg-color: #596D50;
    --el-button-active-border-color: #596D50;
}

.minsu {
    color: white;
    padding-bottom: 100px;/* 维持图片原有比例 */
}
.minsu-pic {
    max-width: 100%; /* 保证图片不超过列宽 */
    height: auto;
    padding-top: -10px;/* 维持图片原有比例 */
}
.minsu-head{
    color: white;
    text-align: center;
    font-weight: bold; /* 加粗 */
    margin-top: 40px; /* 外边距上方 */
    margin-bottom: 40px; /* 外边距下方 */
}

.menu-item {
    font-size: 18px;
    font-weight: bold;
    --el-menu-active-color: #7b996e;
    --el-menu-hover-text-color: #7b996e;
    --el-menu-hover-bg-color: #dce3d8;
}

.logo_word .styled-heading {
    color: #7b996e;        /* 设置字体颜色为绿色 */
    font-size: 25px;
    font-weight: bold;
    padding-top: -20px; /* 加粗字体 */
    margin-left: 10px;
    margin-top: -10px; /* 上边距*/
    margin-bottom: -15px; /* 下边距*/
}



.news-text .title-date {
    display: flex;
    justify-content: space-between; /* 使元素分布在容器的两端 */
    align-items: center; /* 垂直居中对齐 */
}
.news-text .title-date h3 {
    font-weight: bold;
    margin-right: auto; /* 确保h3和日期之间推开 */
}
.news-text .date {
    white-space: nowrap; /* 防止日期折行 */
     /* 增加日期的字体粗细，可选 */
}
.news-text h4 {
    margin-top: 10px; /* 根据需要调整间距 */
}
</style>

<script setup>
import { ref, nextTick, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import newsCard from '@/components/NewsCard.vue'
const { t } = useI18n();
const router = useRouter();
const activeBadge = ref('');

// Functions directly declared and used within setup script
const setActive = (badge) => {
    activeBadge.value = badge; // Update the activeBadge to the current clicked badge
};

const scrollToSection = (sectionId) => {
    nextTick(() => {
        const section = document.querySelector(`.${sectionId}`);
        if (section) {
            section.scrollIntoView({ behavior: 'smooth' });
        }
    });
};

const imageUrls = ref([
    '/images/pic1.png',
    '/images/pic2.png',
    '/images/pic1-1.png',
    '/images/pic1-2.png',
    '/images/pic1-3.png',
]);

const goToAboutPage = () => {
    router.push({ name: 'reservation' });
};

const goToViewPage = () => {
    router.push({ name: 'overview' });
};
</script>
