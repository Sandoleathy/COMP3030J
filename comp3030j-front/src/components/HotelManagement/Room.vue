<template>
    <el-container>
    <!-- 搜索 -->
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="100px" class="custom-form">
        <el-form-item label="Room Number" prop="roomNumber">
          <el-input
              v-model="roomNumber"
              placeholder="Enter room number"
              clearable
              @keyup.enter.native="handleQuery"/>
        </el-form-item>
        <el-form-item label="Room Type" prop="roomType">
          <el-input
              v-model="roomType"
              placeholder="Enter room type"
              clearable
              @keyup.enter.native="handleQuery"/>
        </el-form-item>
        <el-form-item label="Room Volume" prop="maxPeople">
          <el-input
              v-model="maxPeople"
              placeholder="Enter room volume by number of people"
              clearable
              @keyup.enter.native="handleQuery"/>
        </el-form-item>
        <el-form-item label="Bed Type" prop="bedType">
          <el-input
              v-model="bedType"
              placeholder="Enter bed type"
              clearable
              @keyup.enter.native="handleQuery"/>
        </el-form-item>
        <el-form-item label="Bed Length" prop="length">
          <el-input
              v-model="length"
              placeholder="Enter bed length"
              clearable
              @keyup.enter.native="handleQuery"/>
        </el-form-item>
        <el-form-item label="Bed Width" prop="width">
          <el-input
              v-model="width"
              placeholder="Enter bed width"
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
            v-hasPermi="['homestay:room:edit']"
            >Modify</el-button>
        </el-col>
        <el-col :span="1.5">
            <el-button
            type="warning"
            plain
            icon="Download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['homestay:room:export']"
            >Export</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 表单 -->
    <el-table v-loading="loading" :data="roomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="房间类型" align="center" prop="roomType" />
      <el-table-column label="房间号" align="center" prop="roomNumber" />
      <el-table-column label="栋类型" align="center" prop="buildingType" />
      <el-table-column label="床类型" align="center" prop="bedType" />
      <el-table-column label="价格" align="center" prop="roomPrice" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['homestay:room:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['homestay:room:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        :page.sync="pageNum"
        :limit.sync="pageSize"
        @pagination="getList"/>

    <!-- 修改的对话框 -->
    

</template>

<script setup>
import { Edit, Download, Search, Refresh } from '@element-plus/icons-vue';
import axios from 'axios';
import { ref } from 'vue'
import { ElMessage } from 'element-plus';

const roomNumber = ref("")
const roomType = ref("")
const maxPeople = ref("")
const bedType = ref("")
const length = ref("")
const width = ref("")
const image = ref("")

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
            roomNumber: null,
            roomType: null,
            maxPeople: null,
            bedType: null,
            length: null,
            width: null,
            image: null
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
