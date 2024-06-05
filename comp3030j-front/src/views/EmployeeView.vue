<script setup lang="ts">
import navigationBar from '../components/LoginBar.vue'
import { ref, onMounted } from "vue";
import hotelInfo from '../components/HotelManagement/HotelInfo.vue';
import { useI18n } from 'vue-i18n';
const { t } = useI18n();

const activeNum = ref("reservation")
</script>

<template>
  <el-container>
    <el-header style="padding: 0">
      <navigationBar :bar-color="'#409EFF'"></navigationBar>
    </el-header>
    <el-main>
      <hotel-info></hotel-info>
    </el-main>
  </el-container>

</template>

<style scoped>

</style>