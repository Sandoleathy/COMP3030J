<template>
    <el-container class="register-container">
        <el-form ref="registerForm" :model="registerForm" class="register-form" label-position="top">
            <h2>Register</h2>
            <el-form-item label="Username">
                <el-input v-model="username" placeholder="Username"></el-input>
            </el-form-item>
            <el-form-item label="Password">
                <el-input type="password" v-model="password" placeholder="Password" show-password></el-input>
            </el-form-item>
            <el-form-item class="action-items">
                <el-button type="primary" @click="handleRegister">Submit</el-button>
                <el-button type="text" class="login-link" @click="goToLoginPage">Already have account? Log in</el-button>
                <!-- <a href="#/register" class="register-link">Register</a> -->
            </el-form-item>
        </el-form>
    </el-container>
</template>


<script setup>
import { ref } from 'vue';
import { ElForm, ElFormItem, ElInput, ElButton, ElContainer, ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
import axios from 'axios';

const router = useRouter();

var username = ref("")
var password = ref("")

const url = "http://localhost:8080/auth/register"

const handleRegister = () => {
    if(!checkInput()){
        return
    }
    axios.get(url, {
        params: {
            "username": username.value,
            "password": password.value
        }
        
    }).then(response => {
        const data = response.data;
        ElMessage.success("Register successful!")
        // 根据服务器返回的数据进行相应的处理
    }).catch(error => {
        ElMessage.error("Error fetching data" + error)
        //console.error('Error fetching data:', error);
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
/* 全局样式文件或 App.vue 中的 <style> 标签 */
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
.login-link {
    color: #409EFF; /* Element UI 默认蓝色 */
    text-decoration: underline; /* 添加下划线 */
    margin-left: 60px; /* 与登录按钮的间距 */
}
</style>
