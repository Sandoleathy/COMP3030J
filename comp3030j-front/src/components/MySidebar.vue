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
        <el-menu-item index="0" @click="goToMainView">Home
        </el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="1">Order</el-menu-item>
        <el-sub-menu index="2">
            <template #title>Common Information</template>
            <el-menu-item index="2-1">Common Travelers</el-menu-item>
            <el-menu-item index="2-2">Common Address</el-menu-item>
            <el-menu-item index="2-3">Common Contacts</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="3">My Information</el-menu-item>
    </el-menu>
</template>

<style scoped>

</style>