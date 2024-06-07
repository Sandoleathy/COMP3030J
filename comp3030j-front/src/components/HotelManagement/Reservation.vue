<script setup>
import { listReservation, getReservation } from "@/api/homestay/reservation.js";
import { onMounted, ref } from "vue";
import { Edit, Download, Search, Refresh } from '@element-plus/icons-vue';

import axios from '@/axios';
import { ElMessage } from 'element-plus';

const { proxy } = getCurrentInstance();
onMounted(() => {
  getList()
})

const contactInformation = ref("")
const checkinTime = ref("")
const checkoutTime = ref("")
const numberOfGuests = ref("")
const numberOfRooms = ref("")
const reservationTime = ref("")
const totalPrice = ref("")
const pay = ref("")
const reservationStatus = ref("")

const ids = ref([]) //选中数组

const loading = ref(false) //遮罩层
const title = ref("")  //弹出层标题
const open = ref(false) //是否显示弹出层

//需要展示的参数
const queryParams = ref ({
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
})

const reservationList = ref([]) //sheet数据
const total = ref(0) //总条数

const form = ref({
  id: null,
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
})

/** 查询民宿预订列表 */
const getList = () => {
  loading.value = true;
  listReservation(queryParams.value).then(response => {
    reservationList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

/** search按钮操作 */
const handleQuery = () => {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
const resetQuery = () => {
  this.resetForm("queryForm");
  handleQuery();
}

/** 修改按钮操作 */
const handleUpdate = (row) => {
  reset();
  const id = row.id || ids
  getReservation(id).then(response => {
    // this.form = response.data;
    open.value = true;
    title.value = "Modify Reservation Record";
  });
};
const handleSelectionChange = (selection) => {
  ids.value = selection.map(item => item.id)
  single.value = selection.length!==1
  multiple.value = !selection.length
}
const cancel = () => {
  open.value = false;
  reset();
}
const reset = () => {
  form.value = {
    id: null,
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
  };
  proxy.resetForm("form");
}
const submitForm = () => {

}
</script>

<template>

  <el-container>
    <!-- search -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="150px">
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="Check-In Time" prop="checkinTime">
            <el-date-picker clearable
                            v-model="queryParams.checkinTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="Enter check-in time">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="Check-Out Time" prop="checkoutTime">
            <el-date-picker clearable
                            v-model="queryParams.checkoutTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="Enter check-out time">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="Number of Guests" prop="numberOfGuests">
            <el-input
                v-model="queryParams.numberOfGuests"
                placeholder="Enter number of guests"
                clearable
                @keyup.enter.native="handleQuery"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="Number of Rooms" prop="numberOfRooms">
            <el-input
                v-model="queryParams.numberOfRooms"
                placeholder="Enter number of rooms"
                clearable
                @keyup.enter.native="handleQuery"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="Creation Time" prop="reservationTime">
            <el-date-picker clearable
                            v-model="queryParams.reservationTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="Enter the time order creation time">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="Price" prop="totalPrice">
            <el-input
                v-model="queryParams.totalPrice"
                placeholder="Enter price of the order"
                clearable
                @keyup.enter.native="handleQuery"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="Paid or not" prop="pay">
            <el-input
                v-model="queryParams.pay"
                placeholder=""
                clearable
                @keyup.enter.native="handleQuery"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="Reservation Status" prop="reservationStatus">
            <el-input
                v-model="queryParams.reservationStatus"
                placeholder=""
                clearable
                @keyup.enter.native="handleQuery"/>
          </el-form-item>
        </el-col>
        <el-col :span="8" style="text-align: center">
          <el-form-item>
            <el-button type="primary" icon="Search" size="mini" @click="handleQuery">Search</el-button>
            <el-button icon="Refresh" size="mini" @click="resetQuery">Refresh</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-container>

  <!-- sheet -->
  <el-table v-loading="loading" :data="reservationList.value" @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55" align="center" />
    <el-table-column label="id" align="center" prop="hsReservation.id" />

    <el-table-column label="Check-In Time" align="center" prop="checkinTime" width="180">

    </el-table-column>
    <el-table-column label="Check-Out Time" align="center" prop="checkoutTime" width="180">
      <!-- <template slot-scope="scope">
        <span>{{ parseTime(scope.row.checkoutTime, '{y}-{m}-{d}') }}</span>
      </template> -->
    </el-table-column>
    <el-table-column label="Number of Guest" align="center" prop="numberOfGuests" />
    <el-table-column label="Number of Room" align="center" prop="numberOfRooms" />
    <el-table-column label="Reservation Creation Time" align="center" prop="reservationTime" width="180">
      <!-- <template slot-scope="scope">
        <span>{{ parseTime(scope.row.reservationTime, '{y}-{m}-{d}') }}</span>
      </template> -->
    </el-table-column>
    <el-table-column label="Contact" align="center" prop="contactInformation" />
    <el-table-column label="Remark" align="center" prop="requests" />
    <el-table-column label="Price" align="center" prop="totalPrice" />
    <el-table-column label="Paid or not" align="center" prop="pay" />
    <el-table-column label="Reservation Status" align="center" prop="reservationStatus" />
    <el-table-column label="Operation" align="center" class-name="small-padding fixed-width" width="380">
      <el-button
          size="large"
          type="text"
          icon="el-icon-edit"
          @click="handleUpdate(scope.row)"
      >revise</el-button>
    </el-table-column>
  </el-table>

  <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
  />

  <!-- Display time format --> <!-- To be modified -->
  <!--    <p>check in time {{ checkinTime }}</p>
      <p>check out time{{ checkoutTime }}</p>-->

  <!-- Modified dialog box -->
  <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="Contact Information" prop="contactInformation">
        <el-input v-model="forReservation.vuem.contactInformation" type="textarea" placeholder="Enter contact phone number" />
      </el-form-item>
      <el-form-item label="Check-In Time" prop="checkinTime">
        <el-date-picker clearable
                        v-model="form.checkinTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="Choose check-in time">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="Check-Out Time" prop="checkoutTime">
        <el-date-picker clearable
                        v-model="form.checkoutTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="Choose check-out time">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="Number of Guests" prop="numberOfGuests">
        <el-input v-model="form.numberOfGuests" placeholder="Enter number of guests" />
      </el-form-item>
      <el-form-item label="Number of Rooms" prop="numberOfRooms">
        <el-input v-model="form.numberOfRooms" placeholder="Enter number of rooms" />
      </el-form-item>
      <el-form-item label="Reservation Creation Time" prop="reservationTime">
        <el-date-picker clearable
                        v-model="form.reservationTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="Choose the time reservation order is created">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="Remark" prop="requests">
        <el-input v-model="form.requests" type="textarea" placeholder="Enter remark content" />
      </el-form-item>
      <el-form-item label="Price" prop="totalPrice">
        <el-input v-model="form.totalPrice" placeholder="Enter price of the reservation order" />
      </el-form-item>
      <el-form-item label="Paid or not" prop="pay">
        <el-input v-model="form.pay" placeholder="Enter wheater the payment is finished" />
      </el-form-item>
      <el-form-item label="Reservation Status" prop="reservationStatus">
        <el-input v-model="form.reservationStatus" placeholder="Enter reservation status" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">Confirm</el-button>
      <el-button @click="cancel">Cancel</el-button>
    </div>
  </el-dialog>

</template>