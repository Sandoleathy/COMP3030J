<template>
    <div class="navigation_bar" style="width: 100%;">
        <el-container class="container">
            <el-header class="header">
                <loginBar></loginBar>
            </el-header>
            <el-main class="main">
                <firstPage></firstPage>
                <h3>关于我们

                    欢迎来到我们的园区！
                    在这片静谧而充满活力的乡村，我们以实现经济、社会和生态的可持续发展为使命，致力于创造一个独特而温馨的生活空间。
                    在这里，您将体验到乡村生活的魅力和舒适，尽情享受大自然的馈赠。我们的民宿将为您提供舒适的住宿，让您在宁静的乡间度过惬意的时光；医疗保健养老服务中心将为您提供贴心的健康照护和关爱；可持续发展能源中心将引领您走向绿色生活，体验清洁能源的魅力。
                    我们欢迎您的到来，让我们一同探索这片美丽的土地，感受乡村的温暖与包容，共同创造一个更美好的未来！
                    期待您的光临！</h3>
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
