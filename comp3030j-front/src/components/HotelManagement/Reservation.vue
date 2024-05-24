<template>
    <el-container>
    <!-- 搜索 -->
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

    <!-- 修改或下载选项 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['homestay:reservation:edit']"
        >Modify</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['homestay:reservation:export']"
        >Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 表格 -->
    <el-table v-loading="loading" :data="reservationList.value" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="hsReservation.id" />

      <el-table-column label="Check-In Time" align="center" prop="hsReservation.checkinTime" width="180">

      </el-table-column>
      <el-table-column label="Check-Out Time" align="center" prop="hsReservation.checkoutTime" width="180">
        <!-- <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkoutTime, '{y}-{m}-{d}') }}</span>
        </template> -->
      </el-table-column>
      <el-table-column label="Number of Guest" align="center" prop="hsReservation.numberOfGuests" />
      <el-table-column label="Number of Room" align="center" prop="hsReservation.numberOfRooms" />
      <el-table-column label="Reservation Creation Time" align="center" prop="hsReservation.reservationTime" width="180">
        <!-- <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationTime, '{y}-{m}-{d}') }}</span>
        </template> -->
      </el-table-column>
      <el-table-column label="Contact" align="center" prop="hsReservation.contactInformation" />
      <el-table-column label="Remark" align="center" prop="hsReservation.requests" />
      <el-table-column label="Price" align="center" prop="hsReservation.totalPrice" />
      <el-table-column label="Paid or not" align="center" prop="hsReservation.pay" />
      <el-table-column label="Reservation Status" align="center" prop="hsReservation.reservationStatus" />
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
        :page.sync="pageNum"
        :limit.sync="pageSize"
        @pagination="getList"
    />

    <!-- 显示时间的格式 --> <!-- 待修改 -->
<!--    <p>check in time {{ checkinTime }}</p>
    <p>check out time{{ checkoutTime }}</p>-->

    <!-- 修改的对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="Contact Information" prop="contactInformation">
          <el-input v-model="form.contactInformation" type="textarea" placeholder="Enter contact phone number" />
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

<script setup>
import { Edit, Download, Search, Refresh } from '@element-plus/icons-vue';
import axios from 'axios';
import { ref } from 'vue'
import { ElMessage } from 'element-plus';

import { watch } from 'vue';

watch(reservationList, (newVal, oldVal) => {
    // Log the check-in times of all reservations
    newVal.forEach(reservation => {
        console.log("Check-In Time for reservation", reservation.hsReservation.id, ":", reservation.hsReservation.checkinTime);
    });
}, { deep: true });

function logCheckInTime(checkinTime) {
    console.log("Check-In Time:", checkinTime);
    return ''; // Return an empty string because we don't need to output anything in the template
}


const contactInformation = ref("")
const checkinTime = ref("")
const checkoutTime = ref("")
const numberOfGuests = ref("")
const numberOfRooms = ref("")
const reservationTime = ref("")
const totalPrice = ref("")
const pay = ref("")
const reservationStatus = ref("")

var pageNum = 1
var pageSize = 10

//遮罩层
var loading = false;
//弹出层标题
var title = "";
//是否显示弹出层
var open = false;
//选中数组
var ids = [];
//表格的列
var cols = {
    id: '0',
    checkinTime: '2024-05-01',
    checkoutTime: '2024-05-02',
    numberOfGuests: '1',
    numberOfRooms: '1',
    reservationTime: '00:00:00',
    contactInformation: '123456',
    requests: 'ice free',
    totalPrice: '100',
    pay: 'Yes',
    reservationStatus: 'Finished',
    Operation: null
}
//需要展示的参数
var queryParams = {
    pageNum: 1,
    pageSize: 10,
    contactInformation: '',
    checkinTime: '',
    checkoutTime: null,
    numberOfGuests: null,
    numberOfRooms: null,
    reservationTime: null,
    requests: null,
    totalPrice: null,
    pay: null,
    reservationStatus: null
}
//表格数据
var reservationList = [];
//总条数
var total = ref(0);

const listReservation = () => {

};

/** 查询民宿预订列表 */
const getList = () => {
    const token = sessionStorage.getItem("token");
    axios.get("/api/homestay/reservation/list" , {
      params: queryParams.value,
      headers: {
        'Authorization': 'Bearer ' + token
      }
    }).then(response => {
      const data = response.data
      reservationList.value = data.rows;
      total.value=response.data.total;
      console.log("abc", reservationList.value[0].hsReservation.checkinTime)
    }).catch(error => {
      console.error(error)
    })
    /*listReservation(queryParams).then(response => {
        reservationList = response.rows;
        total = response.total;
        loading = false;
    });*/
};

/** 搜索按钮操作 */
const handleQuery = () => {
    pageNum = 1;
    getList();
};
/** 重置按钮操作 */
const resetQuery = () => {
    resetForm("queryForm");
    handleQuery();
};

/** 修改按钮操作 */
const handleUpdate = (row) => {
    reset();
    const id = row.id || ids
    getReservation(id).then(response => {
    // this.form = response.data;
    open = true;
    title = "Modify Reservation Record";
    });
};
/** 导出按钮操作 */
const handleExport = (row) => {
    // this.download('homestay/reservation/export', {
    //     ...this.queryParams
    // }, `reservation_${new Date().getTime()}.xlsx`)
};

</script>