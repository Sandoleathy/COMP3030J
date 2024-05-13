<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="vertical"
             :ellipsis="false" @select="handleSelect">
        <el-menu-item index="0">Home</el-menu-item>
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

<script lang="ts" setup>
import { defineProps, defineEmits } from 'vue';

const props = defineProps({
    activeIndex: String
});

const emit = defineEmits(['update:index']);

const handleSelect = (index: string) => {
    emit('update:index', index);
};
</script>


<style scoped>

</style>