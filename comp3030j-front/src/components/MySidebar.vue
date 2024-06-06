<template>
    <el-menu :default-active="activeIndex" mode="vertical"
             :ellipsis="false" @select="handleSelect">
        <el-menu-item class="menu-item" index="1">{{ t('mySidebar.order') }}</el-menu-item>
        <el-menu-item class="menu-item" index="3">{{ t('mySidebar.myInfo') }}</el-menu-item>
    </el-menu>
</template>

<script lang="ts" setup>
import { defineProps, defineEmits } from 'vue';
import { useI18n } from 'vue-i18n';
const { t } = useI18n();

const props = defineProps({
    activeIndex: String
});

const emit = defineEmits(['update:index']);

const handleSelect = (index: string) => {
    emit('update:index', index);
};
</script>


<style scoped>
.item{
    color: dimgray;
    font-size: 18px;
    font-weight: bold;
    margin-top: 10px;
    margin-left: 15px;
}
</style>