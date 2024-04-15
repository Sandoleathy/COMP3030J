<template>
    <div class="navigation_bar" style="width: 100%;">
        <el-container class="container">
            <el-header class="header">
                <loginBar></loginBar>
            </el-header>
            <el-main class="main">
                <firstPage></firstPage>
            </el-main>
        </el-container>
    </div>
</template>

<script setup>
import api from '../api'
import {onMounted} from 'vue'
import loginBar from '../components/LoginBar.vue'
import firstPage from '../components/FirstPage.vue'

//axios的get方法例子
const clickTest = () => {
    api.get('/users')
        .then(response => {
            console.log(response.data);
        })
        .catch(error => {
            console.error(error);
        });
}

//页面初始化时会先执行的代码
onMounted( () => {
    clickTest()
})
</script>

<style>
.container {
   top:0;
    left: 0;
    position: absolute;
    min-width: 100vw; /* 根据实际内容调整 */
}

@media (max-width: 1024px) {
    .container {
        min-width: 100vw;
    }
}

.header, .main {
    margin: 0;
    padding: 0;
}
</style>
