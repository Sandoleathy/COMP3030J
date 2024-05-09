<template>
    <div class="navigation_bar">
        <el-button type="text" class="nav-btn" @click="goToLoginPage">Log in</el-button>
        <!-- <el-button type="text" class="nav-btn" @click="goToRegisterPage">Register</el-button> -->
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
const router = useRouter();

const goToLoginPage = () => {
    router.push({ name: 'login' });
};

// const goToRegisterPage = () => {
//     router.push({ name: 'register' });
// };
</script>

<style scoped>
.navigation_bar {
    background-color: #409EFF;
    padding: 10px 100px;
    margin: 0;
    text-align: right;
    top: 0;
    position: absolute;
    width: 90vw;
    position: relative;

    left: 0;
    width: 100%; /* 确保导航栏宽度与视窗宽度一致 */
    z-index: 1000; /* 设置一个高的z-index确保导航栏在内容之上 */
    /* 设置背景颜色以确保内容不透明显示 */
}

.nav-btn {
    font-size: 18px;
    font-weight: bold;
    text-decoration: underline;
    color: black;
}
</style>
