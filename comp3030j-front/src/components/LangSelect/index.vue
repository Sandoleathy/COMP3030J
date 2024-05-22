<template>
    <el-dropdown trigger="click" class="international" @command="handleSetLanguage">
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item :disabled="language === 'zh'" command="zh">中文</el-dropdown-item>
                <el-dropdown-item :disabled="language === 'en'" command="en">English</el-dropdown-item>
            </el-dropdown-menu>
        </template>
        <div>
            <router-link   to>Language/语言</router-link>
        </div>
    </el-dropdown>
</template>
<script setup>
import { useI18n } from 'vue-i18n';
import  useAppStore  from '@/store/modules/app.js';
import { ElMessage } from 'element-plus';
import { changeLanguage } from "@/api/login";
import SvgIcon from '@/components/SvgIcon'; // 确保你有这个组件或相应地调整

const { t } = useI18n();
const appStore = useAppStore();
const language = computed(() => appStore.language);

ElMessage({ message: t('settings.languageSuccess'), type: 'success' });

const handleSetLanguage = async (value) => {


    appStore.setLanguage(value); // 假设 setLanguage 方法会更新 Pinia 中的状态并处理 Cookie

    try {
        await changeLanguage(value);
        window.location.reload();

    } catch (error) {
        console.error('Error changing language:', error);
        ElMessage({ message: t('errors.changeLanguageFailed'), type: 'error' });
    }

}
</script>

