<template>
    <el-container>
        <el-header v-model="pageNum">
            <el-button type="primary" @click="changePage(0)">Reservation</el-button>
            <el-button type="primary" @click="changePage(1)">Room</el-button>
            <el-button type="primary" @click="changePage(2)">Buiding</el-button>
            <el-button type="primary" @click="changePage(3)">Employee</el-button>
        </el-header>
        <el-main>
            <div v-if="pageNum=='0'">
                <reservation></reservation>
            </div>
            <div v-if="pageNum=='1'">
                <room></room>
            </div>
            <div v-if="pageNum=='2'">
                <p>buiding</p>
            </div>
            <div v-if="pageNum=='3'">
                <p>emoloyee</p>
            </div>
        </el-main>
    </el-container>
</template>
  
<script setup>
import reservation from './Reservation.vue'
import room from './Room.vue'
import { ref } from 'vue';

const pageNum = ref(0);

function changePage(index) {
  pageNum.value = index;
}
</script>

<style scoped>


</style>
  