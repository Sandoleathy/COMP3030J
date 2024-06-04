<template>
    <div class="container">
        <div id="background"></div>
        <el-container class="register-container">
            <el-form ref="registerForm" :model="registerForm" class="register-form" label-position="top">
                <h2>Register</h2>
                <el-form-item :label="t('register.username')">
                    <el-input v-model="username" :placeholder="t('register.username')"></el-input>
                </el-form-item>
                <el-form-item :label="t('register.password')">
                    <el-input type="password" v-model="password" :placeholder="t('register.password')" show-password></el-input>
                </el-form-item>
                <el-form-item class="action-items">
                    <el-button class="register-button" type="primary" @click="handleRegister" :disabled="isLoading">
                        <span v-if="!isLoading"> {{ t('register.signUp') }}</span>
                        <el-icon v-if="isLoading" class="is-loading">
                            <Loading />
                        </el-icon>
                    </el-button>
                    <el-button type="text" class="login-link" @click="goToLoginPage">{{ t('register.logIn') }}</el-button>
                    <!-- <a href="#/register" class="register-link">Register</a> -->
                </el-form-item>
            </el-form>
        </el-container>
    </div>
</template>


<script setup>
import { ref } from 'vue';
import { ElForm, ElFormItem, ElInput, ElButton, ElContainer, ElMessage, ElIcon } from 'element-plus';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
import axios from '@/axios';

const router = useRouter();

const isLoading = ref(false)
var username = ref("")
var password = ref("")

const url = "/api/auth/register"

const handleRegister = () => {
    if(!checkInput()){
        return
    }
    isLoading.value = true;
    axios.post(url, {
        username: username.value,
        password: password.value 
    }).then(response => {
        isLoading.value = false
        const data = response.data;
        if(data.code == 200){
            //注册成功
            ElMessage.success("Register successful!")
            router.push('login')
        }else{
            ElMessage.error(data.msg)
        }
        // 根据服务器返回的数据进行相应的处理
    }).catch(error => {
        ElMessage.error("Error fetching data" + error)
    });
};

// 回到登录页的按钮跳转

const goToLoginPage = () => {
    router.push({ name: 'login' });
};

const checkInput = () => {
    var isValid = true
    if(username.value == ""){
        ElMessage.warning("Username can not be empty!")
        isValid = false
    }
    if(password.value == ""){
        ElMessage.warning("Password can not be empty!")
        isValid = false
    }
    return isValid
}
</script>

<style scoped>
#background{
    background-color: #f0f0f0; /* 设置背景颜色 */
    background-image: url('/images/login-background.png'); /* 设置背景图片 */
    background-size: cover; /* 图片尺寸适应 */
    background-repeat: no-repeat; /* 不重复平铺 */

    position: absolute; /* 设置绝对定位 */
    width: 100%;
    height: 100vh;
    z-index: -1;
}
.container{
    width: 100%;
}
html, body {
    height: 100%; /* 设置高度为视窗高度 */
    margin: 0; /* 移除默认外边距 */
    padding: 0; /* 移除默认内边距 */
    background-color: #f2f2f2; /* 可以设置为你页面的背景色 */
}

#app {
    height: 100%; /* 确保 Vue 的根元素也占满整个屏幕 */
}

.register-container {
    display: flex;
    width: 100%;
    align-items: center; /* 垂直居中对齐 */
    justify-content: center; /* 水平居中对齐 */
    height: 100vh; /* 设置为视窗的完整高度 */

}

.register-form {
    width: 300px; /* 表单宽度 */
    padding: 35px; /* 内边距 */
    border-radius: 10px; /* 圆角 */
    background-color: white; /* 白色背景 */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 简单的阴影效果增加立体感 */
}

.register-button {
    --el-button-bg-color: #1f1f1f;
    --el-button-border-color: #1f1f1f;
    --el-button-hover-bg-color: grey;
    --el-button-hover-border-color: grey;
    --el-button-active-bg-color: #1f1f1f;
    --el-button-active-border-color: #1f1f1f;
}
.login-link {
    color: #1f1f1f; /* Element UI */
    text-decoration: underline; /* 添加下划线 */
}
</style>
