<script setup>
import { ref, defineEmits } from 'vue';
const emit = defineEmits(['update:index'])

const activeIndex = ref('3')


const updateActiveIndex = (index) => {
    activeIndex.value = index;
    console.log("Emitting index:", index);
    emit('update:index', index);
};



// function goToMainView() {
//     router.push({ name: 'entry' });
// }

</script>

<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="vertical"
             :ellipsis="false" @select="updateActiveIndex">
        <el-menu-item index="0" @click="goToMainView">首页
        </el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="1">订单</el-menu-item>
        <el-sub-menu index="2">
            <template #title>常用信息</template>
            <el-menu-item index="2-1">常用旅客</el-menu-item>
            <el-menu-item index="2-2">常用地址</el-menu-item>
            <el-menu-item index="2-3">常用联系人</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="3">我的信息</el-menu-item>
    </el-menu>
</template>

<style scoped>

</style>