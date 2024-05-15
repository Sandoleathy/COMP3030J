<template>
    <div class="navigation_bar">
        <el-row justify="end">
            <el-col v-if="!isLogin" :span="8" style="margin-top: 10px;">
                <div style="cursor:default;">
                    <span class="clickable" @click="goToLoginPage">Sign in</span> | <span class="clickable" @click="SignUp">Sign up</span>
                </div>
            </el-col>
            <el-col :span="8" v-if="isLogin">
                <div class="user-info">
                    <el-avatar icon="UserFilled" :src="avatarPath"></el-avatar>
                    <el-dropdown @command="handleCommand">
                        <span class="el-dropdown-link">
                            {{ username }}
                            <el-icon class="el-icon--right">
                                <arrow-down />
                            </el-icon>
                        </span>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item command="a">
                                    Profile
                                </el-dropdown-item>
                                <el-dropdown-item command="b">
                                    Log out
                                </el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
            </el-col>
            <el-col :span="6" style="text-align: right;margin-top: 10px">
                <div>
                    <span style="color: white;">
                        {{ year }}/{{ month }}/{{ day }}  {{ hours }}:{{ minutes }}:{{ seconds }}  &nbsp; <i :class="icon"></i> {{ temperature }}°C 
                    </span>
                </div>
            </el-col>
        </el-row>
        
        
        
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref, onMounted } from 'vue'
import { ElAvatar, ElText, ElDropdownMenu, ElDropdown, ElDropdownItem, ElMessage } from 'element-plus';
import { ArrowDown } from '@element-plus/icons-vue'
import axios from 'axios'

const router = useRouter();
const isLogin = ref(false)
const username = ref("")
const avatarPath = ref("")

const goToLoginPage = () => {
    router.push({ name: 'login' });
};

const SignUp = () => {
    router.push({ name: 'register' });
}

//处理时间的部分
var currentDate = new Date();
var year = ref(0)
var month = ref(0)
var day = ref(0)
var hours = ref(0)
var minutes = ref(0)
var seconds = ref(0)

const updateTime = () => {
    currentDate = new Date();
    year.value = currentDate.getFullYear(); // 年份
    month.value = currentDate.getMonth() + 1; // 月份（注意月份从 0 开始，所以要加 1）
    day.value = currentDate.getDate(); // 当月的日期
    hours.value = currentDate.getHours(); // 小时
    minutes.value = currentDate.getMinutes(); // 分钟
    seconds.value = currentDate.getSeconds(); // 秒
}
//----------------------------------------------
//天气数据
let location = "117.13,40.29";
let key = "76cbdc094c49493b9a1c3f7f6d659ff4";
const temperature = ref(0)
const icon = ref('')
const getWeather = () => {
    axios.get("https://devapi.qweather.com/v7/weather/now",{
        params: {
            location: location,
            key: key,
            lang: "en"
        }
    }).then(response => {
        const data = response.data
        temperature.value = data.now.temp
        icon.value = 'qi-' + data.now.icon
    }).catch(error => {
        ElMessage.error("Fetch weather information failed")
        //console.log(error)
    })
}
//----------------------------------------------
const handleCommand = (command) => {
    if(command == "a"){
        //myview
        router.push("/My")
    }else if(command == "b"){
        sessionStorage.removeItem("token")
        sessionStorage.removeItem("username")
        window.location.reload()
    }
}

onMounted( ()=> {
    setInterval(updateTime,1000)
    getWeather()
    if(sessionStorage.getItem('token') != null){
        isLogin.value = true;
        username.value = sessionStorage.getItem('username')
        avatarPath.value = sessionStorage.getItem('avatar')
        //console.log(avatarPath.value)
    }else{
        isLogin.value = false
    }
} ) 

// const goToRegisterPage = () => {
//     router.push({ name: 'register' });
// };
</script>

<style scoped>
@import url('https://cdn.jsdelivr.net/npm/qweather-icons@1.3.0/font/qweather-icons.css');
.clickable{
    color: white;
    cursor: pointer;
}
.clickable:hover{
    color: black;
}
.navigation_bar {
    background-color: #409EFF;
    padding: 10px 20px;
    margin: 0;
    text-align: right;
    top: 0;
    position: absolute;
    width: 90vw;
    position: relative;
    padding-bottom: 10px;

    min-height: 60px;
    max-height: 60px;
    left: 0;
    width: 100%; /* 确保导航栏宽度与视窗宽度一致 */
    z-index: 1000;
    /* 设置背景颜色以确保内容不透明显示 */
}

.nav-btn {
    font-size: 18px;
    font-weight: bold;
    text-decoration: underline;
    color: black;
}
.user-info{
    vertical-align: middle;
}
.el-dropdown-link {
  cursor: pointer;
  color: white;
  display: flex;
  align-items: center;
  margin-top: 10px;
  margin-left: 10px;
  font-size: 16px;
}
.el-dropdown-link:hover{
  border: none;
}
</style>
