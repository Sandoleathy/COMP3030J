<template>
    <div class="navigation_bar" style="width: 100%;">
        <el-container class="container">
            <el-header class="header">
                <loginBar></loginBar>
            </el-header>
            <el-main class="main">
                <firstPage></firstPage>
            </el-main>
            <el-footer class="footer">
                <Footer></Footer>
            </el-footer>
        </el-container>
    </div>

</template>

<script setup>
import api from '../api'
import {onMounted} from 'vue'
import loginBar from '../components/LoginBar.vue'
import firstPage from '../components/FirstPage.vue'
import Footer from '../components/Footer.vue'
import { ElContainer, ElHeader, ElMain, ElFooter } from 'element-plus'


//页面初始化时会先执行的代码
onMounted( () => {
    
})
</script>

<style>
.container {
   top:0;
    left: 0;
    position: absolute;
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
.footer{
    padding: 0;
}
</style>
