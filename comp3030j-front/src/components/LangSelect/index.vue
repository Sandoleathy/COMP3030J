<template>
    <el-dropdown trigger="click" class="international" @command="handleSetLanguage">
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item :disabled="language === 'zh'" command="zh">中文</el-dropdown-item>
                <el-dropdown-item :disabled="language === 'en'" command="en">English</el-dropdown-item>
            </el-dropdown-menu>
        </template>
        <div >
            <router-link   to class="international1">
              Language/语言
              <el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
            </router-link>
        </div>
    </el-dropdown>
</template>
<script setup>
import { useI18n } from 'vue-i18n';
import  useAppStore  from '@/store/modules/app.js';
import { ElMessage } from 'element-plus';
import { changeLanguage } from "@/api/login";
import SvgIcon from '@/components/SvgIcon'; // 确保你有这个组件或相应地调整
import Cookies from 'js-cookie';
import {ArrowDown} from "@element-plus/icons-vue";

const { t } = useI18n();
const appStore = useAppStore();
const language = computed(() => appStore.language);

if(Cookies.get("showChangeMessage")){
    ElMessage({ message: t('settings.languageSuccess'), type: 'success' });
    Cookies.remove("showChangeMessage")
}


const handleSetLanguage = async (value) => {


    appStore.setLanguage(value); // 假设 setLanguage 方法会更新 Pinia 中的状态并处理 Cookie
    Cookies.set("showChangeMessage",true)
    window.location.reload();
}
</script>
<style>
.international1{
  color: white;
  vertical-align: center;
  margin: auto;
}
</style>

