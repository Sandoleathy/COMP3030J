<script setup lang="ts">
import navigationBar from '../components/LoginBar.vue'
import { ref, onMounted } from "vue";
import reservation from '../components/Employee/Reservations.vue'
import checkIn from '../components/Employee/CheckIn.vue'
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
      <el-tabs v-model="activeNum">
        <el-tab-pane name="reservation" :label="t('employeeView.reservation')"></el-tab-pane>
        <el-tab-pane name="checkIn" :label="t('employeeView.checkIn')"></el-tab-pane>
        <el-tab-pane name="checkOut" :label="t('employeeView.checkOut')"></el-tab-pane>
      </el-tabs>
    </el-main>
    <el-main>
      <div v-if="activeNum === 'reservation'">
        <reservation></reservation>
      </div>
      <div v-if="activeNum === 'checkIn'">
        <checkIn></checkIn>
      </div>
      <div v-if="activeNum === 'checkOut'">
        checkOut
      </div>
    </el-main>
  </el-container>

</template>

<style scoped>

</style>