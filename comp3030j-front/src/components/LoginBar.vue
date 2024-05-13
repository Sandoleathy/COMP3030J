<template>
    <div class="navigation_bar">
        <el-button type="text" class="nav-btn" @click="goToLoginPage" v-if="!isLogin">Log in</el-button>
        <div v-if="isLogin" class="user-info">
            <el-avatar icon="UserFilled"></el-avatar>
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
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref, onMounted } from 'vue'
import { ElAvatar, ElText, ElDropdownMenu, ElDropdown, ElDropdownItem, ElMessage } from 'element-plus';
import { ArrowDown } from '@element-plus/icons-vue'

const router = useRouter();
const isLogin = ref(false)
const username = ref("")

const goToLoginPage = () => {
    router.push({ name: 'login' });
};

const handleCommand = (command) => {
    if(command == "a"){
        //myview
        router.push("/My")
    }else if(command == "b"){
        sessionStorage.removeItem("token")
        sessionStorage.removeItem("username")
        location.reload()
    }
}

onMounted( ()=> {
    if(sessionStorage.getItem('token') != null){
        isLogin.value = true;
        username.value = sessionStorage.getItem('username')
    }else{
        isLogin.value = false
    }
} ) 

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
    padding-bottom: 10px;

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
