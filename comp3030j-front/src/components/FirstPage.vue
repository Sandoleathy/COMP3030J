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
                            <h3 class="styled-heading" style="font-size: 40px;">Yishan</h3>
                        </el-row>
                        <el-row>
                            <h3 class="styled-heading" style="font-size: 25px;">Mountain Villa</h3>
                        </el-row>
                    </div>
                </el-menu-item>
                <div class="flex-grow" />
                <el-menu-item class="menu-item" index="1" @click="scrollToSection('aboutUs')">About Us</el-menu-item>
                <el-menu-item class="menu-item" index="2" @click="goToViewPage">Overview</el-menu-item>
                <el-menu-item class="menu-item" index="3" @click="scrollToSection('news')">News & Notices</el-menu-item>
                <el-menu-item class="menu-item" index="4" @click="scrollToSection('minsu')" style="margin-right: 20px;">Hotel</el-menu-item>
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
        
    <div style="width: 90%; margin: auto;">
        <div class="aboutUs" ref="aboutUsSection">
            <h1 class="line1"></h1>
            <h1 class="header">About Us</h1>
            <h3 class="welcome">- Welcome to our park -</h3>
            <h4 class="context">With a mission to achieve economic, social and ecological sustainability, we are committed to creating a unique and welcoming living space in this quiet and vibrant countryside.
                Here, you will experience the charm and comfort of rural life and enjoy the gifts of nature. Our homestay will provide you with comfortable accommodation, so that you can spend a pleasant time in the quiet country; Health care service center will provide you with intimate health care and love; The Sustainable Energy Center will lead you to a green life and experience the charm of clean energy.
                We welcome your arrival, let us explore this beautiful land together, feel the warmth and tolerance of the countryside, and create a better future together!</h4>
            <h3 class="ending">Looking forward to your visit!</h3>
        </div>

        <div class="news" ref="newsSection">
            <h1 class="line2"></h1>
            <h1 class="minsu-head">News & Notices</h1>
            <div class="news-container">
                <div class="news-text">
                    <div class="title-date">
                        <h3>Yishan Villa May farm activities updated!</h3>
                        <span class="date">2024/04/19</span>
                    </div>
                    <h4>Peach picking can be experienced at the farm every weekend from 10:00 am to 16:00 PM</h4>
                    <hr>
                </div>
            </div>
        </div>

        <div class="minsu" ref="minsuSection">
            <h1 class="line2"></h1>
            <h1 class="minsu-head">Hotel</h1>
            <h3 class="minsu-head">Welcome to our guesthouse, the ideal place for country living！</h3>
            <el-row gutter={50}>
                <el-col :span="12" class="image-column">
                    <img class="minsu-pic" src="/images/pic1.png" alt="民宿图片">
                </el-col>
                <el-col :span="12" class="text-column">
                    <div class="text-content">
                        <p>Our guesthouse is situated in a lush mountain forest,
                          with a quiet environment, fresh air and simple, comfortable rooms.
                          Here you can enjoy quiet time away from the hustle and bustle.
                          In the morning, you will be awakened by the dawn,
                          the birdsong in the valley and the clear spring to accompany you to welcome a new day.
                          During the day, you can stroll along the country roads and experience nature's artistry.
                          At night, you can look up at the stars and share a good time with family and friends.
                        <br><br>Our hotel also offers you a wide range of activities and experiences.
                          You can take part in farm picking activities to savour fresh produce,
                          or participate in handicraft making to experience the charm of traditional craftsmanship.
                        <br><br> Whether you want to relax and unwind or experience the countryside,
                          we will be happy to provide you with the best service to make your holiday a memorable one!
                        </p>
                        <el-button type="primary" class="reservation-button" @click="goToAboutPage">Reservation</el-button>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div></div>
</template>

<style scoped>
.background {
    background-image: url('/images/pic1.png');
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
    background-color: #d3dce680;
}
.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce680;
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

.line2 {
    border: none; /* 移除默认边框 */
    height: 1.5px; /* 线的高度 */
    background-color: #7b996e; /* 线的颜色 */
    /* 上下外边距，可调整 */
    margin: 90px auto 40px;
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
    max-width: 100%; /* 控制文本块的最大宽度 */
}

.reservation-button {
    margin-top: 20px; /* 预订按钮与文本间距 */
    --el-button-bg-color: #7b996e;
    --el-button-border-color: #7b996e;
    --el-button-hover-bg-color: #dce3d8;
    --el-button-hover-border-color: #dce3d8;
}

.minsu {
    padding-bottom: 100px;/* 维持图片原有比例 */
}
.minsu-pic {
    max-width: 100%; /* 保证图片不超过列宽 */
    height: auto;
    padding-top: -10px;/* 维持图片原有比例 */
}
.minsu-head{
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

.news-container {
    width: 90%; /* 或根据实际需要调整宽度 */
    margin: 20px auto; /* 居中显示，并添加外边距 */
    padding: 20px; /* 内边距 */
    border: 1px solid #ccc; /* 边框样式 */
    box-shadow: 0 2px 5px rgba(0,0,0,0.1); /* 轻微的阴影效果 */
    background: #fff; /* 背景颜色 */
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
]);

const goToAboutPage = () => {
    router.push({ name: 'reservation' });
};

const goToViewPage = () => {
    router.push({ name: 'overview' });
};
</script>
