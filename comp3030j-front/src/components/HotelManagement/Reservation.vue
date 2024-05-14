<template>
    <el-container>
    <!-- 搜索 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="68px">
    <el-form-item label="Check-In Time" prop="checkinTime">
        <el-date-picker clearable
            v-model="checkinTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="Please enter check-in time">
        </el-date-picker>
    </el-form-item>
    <el-form-item label="Check-Out Time" prop="checkoutTime">
        <el-date-picker clearable
            v-model="checkoutTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="Please enter check-out time">
        </el-date-picker>
    </el-form-item>
    <el-form-item label="Number of Guest" prop="numberOfGuests">
        <el-input
            v-model="numberOfGuests"
            placeholder="Please enter number of guest"
            clearable
            @keyup.enter.native="handleQuery"/>
    </el-form-item>
    <el-form-item label="Number of Room" prop="numberOfRooms">
        <el-input
            v-model="numberOfRooms"
            placeholder="Please enter number of room"
            clearable
            @keyup.enter.native="handleQuery"/>
    </el-form-item>
    <el-form-item label="Reservation Creation Time" prop="reservationTime">
        <el-date-picker clearable
            v-model="reservationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="Please enter the time reservation order is created">
        </el-date-picker>
    </el-form-item>
    <el-form-item label="Price" prop="totalPrice">
        <el-input
            v-model="totalPrice"
            placeholder="Please enter price of the order"
            clearable
            @keyup.enter.native="handleQuery"/>
    </el-form-item>
    <el-form-item label="Paid or not" prop="pay">
        <el-input
            v-model="pay"
            placeholder="Please enter wheater the payment is finished"
            clearable
            @keyup.enter.native="handleQuery"/>
    </el-form-item>
    <el-form-item label="Reservation Status" prop="reservationStatus">
        <el-input
            v-model="reservationStatus"
            placeholder="Please enter reservation status"
            clearable
            @keyup.enter.native="handleQuery"/>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" icon="Search" size="mini" @click="handleQuery">Search</el-button>
        <el-button icon="Refresh" size="mini" @click="resetQuery">Refresh</el-button>
    </el-form-item>
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

    <!-- 表单 -->
    <el-table v-loading="loading" :data="reservationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="Check-In Time" align="center" prop="checkinTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkinTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Check-Out Time" align="center" prop="checkoutTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkoutTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Number of Guest" align="center" prop="numberOfGuests" />
      <el-table-column label="Number of Room" align="center" prop="numberOfRooms" />
      <el-table-column label="Reservation Creation Time" align="center" prop="reservationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Contact" align="center" prop="contactInformation" />
      <el-table-column label="Remark" align="center" prop="requests" />
      <el-table-column label="Price" align="center" prop="totalPrice" />
      <el-table-column label="Paid or not" align="center" prop="pay" />
      <el-table-column label="Reservation Status" align="center" prop="reservationStatus" />
      <el-table-column label="Operation" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['homestay:reservation:edit']"
          >修改</el-button>
        </template>
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
    <p>check in time {{ checkinTime }}</p>
    <p>check out time{{ checkoutTime }}</p>

    <!-- 修改的对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户联系方式" prop="contactInformation">
          <el-input v-model="form.contactInformation" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="入住时间" prop="checkinTime">
          <el-date-picker clearable
            v-model="form.checkinTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择入住时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="退房时间" prop="checkoutTime">
          <el-date-picker clearable
            v-model="form.checkoutTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择退房时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客户数量" prop="numberOfGuests">
          <el-input v-model="form.numberOfGuests" placeholder="请输入客户数量" />
        </el-form-item>
        <el-form-item label="房间数量" prop="numberOfRooms">
          <el-input v-model="form.numberOfRooms" placeholder="请输入房间数量" />
        </el-form-item>
        <el-form-item label="订单创建/结束时间" prop="reservationTime">
          <el-date-picker clearable
            v-model="form.reservationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择订单创建/结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="requests">
          <el-input v-model="form.requests" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="总价格" prop="totalPrice">
          <el-input v-model="form.totalPrice" placeholder="请输入总价格" />
        </el-form-item>
        <el-form-item label="是否支付" prop="pay">
          <el-input v-model="form.pay" placeholder="请输入是否支付" />
        </el-form-item>
        <el-form-item label="订单状态" prop="reservationStatus">
          <el-input v-model="form.reservationStatus" placeholder="请输入订单状态" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

</template>

<script setup>
import { Edit, Download, Search, Refresh } from '@element-plus/icons-vue';
import axios from 'axios';
import { ref } from 'vue'
import { ElMessage } from 'element-plus';

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

/** 查询民宿预订列表 */
const getList = () => {
    axios.get("url" , {
        params: {
            checkinTime: null,
            checkoutTime: null,
            numberOfGuests: null,
            numberOfRooms: null,
            reservationTime: null,
            totalPrice: null,
            pay: null,
            reservationStatus: null
        }
    }).then(response => {
        const data = response.data
    }).catch(error => {
        console.error(error)
    })
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