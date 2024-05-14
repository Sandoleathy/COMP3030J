<template>
    
    <div class="container">
        <div id="background">

        </div>
        <el-container class="login-container">
            <el-form class="login-form" label-position="top">
                <h2>Log In</h2>
                <el-form-item label="Username">
                    <el-input v-model="username" placeholder="Username"></el-input>
                </el-form-item>
                <el-form-item label="Password">
                    <el-input type="password" v-model="password" placeholder="Password" show-password></el-input>
                </el-form-item>
                <el-form-item class="action-items">
                    <el-button type="primary" @click="handleLogin" :disabled="isLoading">
                        <span v-if="!isLoading">Log in</span>
                        <el-icon v-if="isLoading" class="is-loading">
                            <Loading />
                        </el-icon>
                    </el-button>
                    <router-link class="link" to="register" style="margin-left: 20px;">Register</router-link>
                </el-form-item>
                
            </el-form>
        </el-container>
    </div>
    
</template>


<script setup>
import { ref } from 'vue';
import { ElForm, ElFormItem, ElInput, ElButton, ElContainer, ElMessage, ElIcon } from 'element-plus';
import axios from 'axios';
import { useRouter } from 'vue-router';

var username = ref("")
var password = ref("")
const router = useRouter();
const isAdmin = ref(false)

const isLoading = ref(false)
const url = "/api/auth/login"

const handleLogin = async () => {
    //console.log(username.value)
    
    if(!checkInput()){
        return
    }
    isLoading.value = true;
    try {
        const response = await axios.post(url, {
            username: username.value,
            password: password.value  
        });
        const data = response.data;
        console.log(data)
        if(data.code == 200){
            ElMessage.success("Login successful! Welcome back ")
            //登陆成功,进行后续处理
            sessionStorage.setItem("token" , data.data.access_token)
            sessionStorage.setItem("username" , username.value)
            await getUserType(data.data.access_token) // 等待 getUserType 方法执行完成
            isLoading.value = false
            //console.log(isAdmin.value)
            if(isAdmin.value == true){
                router.push('/admin')
            }else{
                router.push('/')
            }
        }else{
            ElMessage.error(data.msg)
        }
    } catch (error) {
        isLoading.value = false
        ElMessage.error("Error fetching data" + error)
        //console.error('Error fetching data:', error);
    }
};

const getUserType = async (token) => {
    try {
        const response = await axios.get('api/system/user/profile', {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });
        const data = response.data;
        if(data.data.admin == true){
            sessionStorage.setItem("isAdmin" , true)
            isAdmin.value = true
        }else{
            sessionStorage.setItem("isAdmin" , false)
        }
        //console.log(sessionStorage.getItem("isAdmin"))
    } catch (error) {
        console.error(error)
    }
}

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
    background-image: url('/images/login-background.jpg'); /* 设置背景图片 */
    background-size: cover; /* 图片尺寸适应 */
    background-repeat: no-repeat; /* 不重复平铺 */

    position: absolute; /* 设置绝对定位 */
    width: 100%;
    height: 100vh;
    z-index: -1;
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
.container{
    width: 100%;
}
.login-container {
    display: flex;
    width: 100%;
    align-items: center; /* 垂直居中对齐 */
    justify-content: center; /* 水平居中对齐 */
    height: 100vh; /* 设置为视窗的完整高度 */

}

.login-form {
    width: 300px; /* 表单宽度 */
    padding: 35px; /* 内边距 */
    border-radius: 10px; /* 圆角 */
    background-color: white; /* 白色背景 */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 简单的阴影效果增加立体感 */
}
.register-link {
    color: #409EFF; /* Element UI 默认蓝色 */
    text-decoration: underline; /* 添加下划线 */
    margin-left: 60px; /* 与登录按钮的间距 */
}
.link {
    color: #409EFF;
    border-radius: 10px;
}
</style>
