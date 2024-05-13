<template>
    <el-container>
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="68px">
    <el-form-item label="入住时间" prop="checkinTime">
        <el-date-picker clearable
            v-model="queryParams.checkinTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择入住时间">
        </el-date-picker>
    </el-form-item>
    <el-form-item label="退房时间" prop="checkoutTime">
        <el-date-picker clearable
            v-model="queryParams.checkoutTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择退房时间">
        </el-date-picker>
    </el-form-item>
    <el-form-item label="客户数量" prop="numberOfGuests">
        <el-input
            v-model="queryParams.numberOfGuests"
            placeholder="请输入客户数量"
            clearable
            @keyup.enter.native="handleQuery"/>
    </el-form-item>
    <el-form-item label="房间数量" prop="numberOfRooms">
        <el-input
            v-model="queryParams.numberOfRooms"
            placeholder="请输入房间数量"
            clearable
            @keyup.enter.native="handleQuery"/>
    </el-form-item>
    <el-form-item label="订单创建/结束时间" prop="reservationTime">
        <el-date-picker clearable
            v-model="queryParams.reservationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择订单创建/结束时间">
        </el-date-picker>
    </el-form-item>
    <el-form-item label="总价格" prop="totalPrice">
        <el-input
            v-model="queryParams.totalPrice"
            placeholder="请输入总价格"
            clearable
            @keyup.enter.native="handleQuery"/>
    </el-form-item>
    <el-form-item label="是否支付" prop="pay">
        <el-input
            v-model="queryParams.pay"
            placeholder="请输入是否支付"
            clearable
            @keyup.enter.native="handleQuery"/>
    </el-form-item>
    <el-form-item label="订单状态" prop="reservationStatus">
        <el-input
            v-model="queryParams.reservationStatus"
            placeholder="请输入订单状态"
            clearable
            @keyup.enter.native="handleQuery"
        />
    </el-form-item>
    <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
    </el-form-item>
    </el-form>
    </el-container>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
</template>

<script setup>
import axios from 'axios';

//遮罩层
let loading = true;
// 查询参数
let queryParams = {
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
};

/** 查询民宿预订列表 */
const getList = () => {
    loading = true;
    axios.get("url" , {
        params: {

        }
    }).then(response => {
        const data = response.data
    }).catch(error => {
        console.error(error)
    })
    listReservation(this.queryParams).then(response => {
        this.reservationList = response.rows;
        this.total = response.total;
        loading = false;
    });
};

/** 搜索按钮操作 */
const handleQuery = () => {
    queryParams.pageNum = 1;
    getList();
};
/** 重置按钮操作 */
const resetQuery = () => {
    this.resetForm("queryForm");
    this.handleQuery();
};
</script>