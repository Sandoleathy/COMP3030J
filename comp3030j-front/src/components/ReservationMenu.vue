<script lang="ts" setup>
import { ref, nextTick, onMounted } from 'vue'
import axios from 'axios'
import router from "@/router";
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
function goToMyView() {
    router.push({ name: 'my' });
}

function goToMainView() {
    router.push({ name: 'entry' });
}
function goToWeatherView() {
    router.push({ name: 'weather' });
}
const avatarPath = ref("")
const getUserInformation = async () => {
  try {
    const token = sessionStorage.getItem("token");
    //console.log("Token is:", token);
    const response = await axios.get("/api/system/user/profile", {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    });

    const data = response.data;
    console.log(data)
    avatarPath.value = data.data.avatar;
  } catch (error) {
    console.error('Error fetching data:', error);
  }
}
onMounted(() => {
  getUserInformation()
})
</script>

<template>
    <el-menu class="el-menu-demo fixed-top" mode="horizontal"
             :ellipsis="false">
        <el-menu-item index="0">
            <img
                style="width: 50px;height: 50px"
                src="/images/Logo.jpg"
                alt="Element logo"
            />
            <div class="flex-container">
                <h3 class="styled-heading" style="font-size:30px; margin-left: 10px;">Yishan</h3>
                <h3 class="styled-heading" style="margin-top: 10px; margin-left: 5px;">Mountain Villa</h3>
            </div>
        </el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="1" @click="goToMainView"> {{ t('reservationMenu.home') }}</el-menu-item>
        <el-menu-item index="2">{{ t('reservationMenu.order') }}</el-menu-item>
        <el-menu-item index="3" @click="goToWeatherView">{{ t('reservationMenu.weather') }}</el-menu-item>
        <el-menu-item index="4">
            <div class="demo-type">
                <el-avatar @click="goToMyView" :src="avatarPath">  </el-avatar>
            </div>
        </el-menu-item>

    </el-menu>
</template>

<style scoped>

.flex-grow {
    flex-grow: 1;
}
.fixed-top{
    padding: 0 25px;
    margin: 0;
    text-align: right;
    top: 0;
    position: fixed;
    left: 0;
    width: 100%; /* 确保导航栏宽度与视窗宽度一致 */
    z-index: 1000;
}

.demo-type {
    display: flex;
}
.demo-type > div {
    flex: 1;
    text-align: center;
}

.demo-type > div:not(:last-child) {
    border-right: 1px solid var(--el-border-color);
}

.flex-container {
  display: flex;
  align-items: center;  /* 垂直居中 */
}
.styled-heading {
    color: #7b996e;        /* 设置字体颜色为绿色 */
    font-weight: bold;
}
</style>