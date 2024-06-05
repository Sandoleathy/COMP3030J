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
    requests: '无special requirements',
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
        <el-table-column prop="contactInformation" label="contact information"></el-table-column>
        <el-table-column prop="checkinTime" label="check in time"></el-table-column>
        <el-table-column prop="checkoutTime" label="check-out time"></el-table-column>
        <el-table-column prop="numberOfGuests" label="Number of guests"></el-table-column>
        <el-table-column prop="numberOfRooms" label="number of rooms"></el-table-column>
        <el-table-column prop="reservationTime" label="Booking time"></el-table-column>
        <el-table-column prop="requests" label="special requirements"></el-table-column>
        <el-table-column prop="totalPrice" label="Total cost"></el-table-column>
        <el-table-column prop="pay" label="Payment status">
          <template v-slot="scope">
            {{ scope.row.pay === 0 ? 'unpaid' : 'Paid' }}
          </template>
        </el-table-column>
        <el-table-column prop="reservationStatus" label="Booking status"></el-table-column>
        <el-table-column prop="userId" label="User's unique identifier"></el-table-column>
        <el-table-column label="operate">
          <template v-slot="scope">
            <el-button @click="handleEdit(scope.row)" type="primary" size="small">Revise</el-button>
            <el-button @click="handleDelete(scope.row)" type="danger" size="small">delete</el-button>
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