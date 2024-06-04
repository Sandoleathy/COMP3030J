<script setup>
import axios from '@/axios'
import { ref, onMounted } from "vue";

const queryParams = {
  pageNum: 1,
  pageSize: 10,
  contactInformation: null,
  checkinTime: null,
  checkoutTime: null,
  numberOfGuests: null,
  numberOfRooms: null,
  reservationTime: null,
  requests: null,
  totalPrice: null,
  pay: null,
  reservationStatus: null
}
const reservations = ref([
  /* example
  {
    contactInformation: 'example@example.com',
    checkinTime: '2024-05-01 14:00',
    checkoutTime: '2024-05-02 12:00',
    numberOfGuests: 2,
    numberOfRooms: 1,
    reservationTime: '2024-04-15 10:00',
    requests: '无特殊要求',
    totalPrice: 200,
    pay: 0,
    reservationStatus: '已预订',
    userId: '123456789'
  },*/
  // 可以添加更多的预订数据
]);

const getReservations = () => {
  const token = sessionStorage.getItem('token')
  axios.get('/api/homestay/reservation/list', {
    headers: {
      'Authorization': 'Bearer ' + token
    },
    params: queryParams
  }).then(response => {
    console.log(response)
  }).catch(error => {
    console.log(error)
  })
}

onMounted(() => {
  getReservations()
})
</script>

<template>
  <div class="filter-div">

  </div>
  <div class="reservations">
    <div>
      <el-table :data="reservations" style="width: 100%">
        <el-table-column prop="contactInformation" label="联系信息"></el-table-column>
        <el-table-column prop="checkinTime" label="入住时间"></el-table-column>
        <el-table-column prop="checkoutTime" label="退房时间"></el-table-column>
        <el-table-column prop="numberOfGuests" label="客人数"></el-table-column>
        <el-table-column prop="numberOfRooms" label="房间数"></el-table-column>
        <el-table-column prop="reservationTime" label="预订时间"></el-table-column>
        <el-table-column prop="requests" label="特殊要求"></el-table-column>
        <el-table-column prop="totalPrice" label="总价格"></el-table-column>
        <el-table-column prop="pay" label="支付状态">
          <template v-slot="scope">
            {{ scope.row.pay === 0 ? '未支付' : '已支付' }}
          </template>
        </el-table-column>
        <el-table-column prop="reservationStatus" label="预订状态"></el-table-column>
        <el-table-column prop="userId" label="用户的唯一标识符"></el-table-column>
        <el-table-column label="操作">
          <template v-slot="scope">
            <el-button @click="handleEdit(scope.row)" type="primary" size="small">修改</el-button>
            <el-button @click="handleDelete(scope.row)" type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<style scoped>
.reservations{
  width: 100%;
}
</style>