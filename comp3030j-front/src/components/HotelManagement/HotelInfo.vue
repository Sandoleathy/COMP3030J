<template>
    <el-container>
        <el-header v-model="pageNum">
            <el-button
                class="section"
                :class="{ active: pageNum === 0 }"
                type="primary"
                @click="changePage(0)">
              Reservation
            </el-button>
            <el-button
                class="section"
                :class="{ active: pageNum === 1 }"
                type="primary"
                @click="changePage(1)">
              Room
            </el-button>
            <el-button
                class="section"
                :class="{ active: pageNum === 2 }"
                type="primary"
                @click="changePage(2)">
              Building
            </el-button>
            <el-button
                class="section"
                :class="{ active: pageNum === 3 }"
                type="primary"
                @click="changePage(3)">
              Employee
            </el-button>
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
.section {
    border-radius: 2px;
    --el-button-text-color: rgba(64, 158, 255, 1);
    --el-button-hover-border-color: rgba(64, 158, 255, 1);
    --el-button-active-border-color: rgba(64, 158, 255, 1);
    --el-button-bg-color: rgba(255, 255, 255, 0);
    --el-button-hover-bg-color: rgba(64, 158, 255, 0.5);
    --el-button-active-bg-color: rgba(64, 158, 255, 1);
}
.active {
  --el-button-text-color: rgba(255, 255, 255, 1);
  --el-button-bg-color: rgba(64, 158, 255, 1);
  --el-button-hover-bg-color: rgba(64, 158, 255, 0.5);
  --el-button-active-bg-color: rgba(64, 158, 255, 1);
}
</style>
  