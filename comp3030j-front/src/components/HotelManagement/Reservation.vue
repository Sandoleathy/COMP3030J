<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="入住时间" prop="checkinTime">
        <el-date-picker clearable
                        v-model="queryParams.checkinTime"
                        type="date"
                        placeholder="请选择入住时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="退房时间" prop="checkoutTime">
        <el-date-picker clearable
                        v-model="queryParams.checkoutTime"
                        type="date"
                        placeholder="请选择退房时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="客户数量" prop="numberOfGuests">
        <el-input
            v-model="queryParams.numberOfGuests"
            placeholder="请输入客户数量"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房间数量" prop="numberOfRooms">
        <el-input
            v-model="queryParams.numberOfRooms"
            placeholder="请输入房间数量"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单创建/结束时间" prop="reservationTime">
        <el-date-picker clearable
                        v-model="queryParams.reservationTime"
                        type="date"
                        placeholder="请选择订单创建/结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="总价格" prop="totalPrice">
        <el-input
            v-model="queryParams.totalPrice"
            placeholder="请输入总价格"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否支付" prop="pay">
        <el-input
            v-model="queryParams.pay"
            placeholder="请输入是否支付"
            clearable
            @keyup.enter.native="handleQuery"
        />
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
        <el-button type="primary" icon="search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="plus"
            size="mini"
            @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reservationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="hsReservation.id" />
      <el-table-column label="入住时间" align="center" prop="hsReservation.checkinTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.hsReservation.checkinTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="退房时间" align="center" prop="hsReservation.checkoutTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.hsReservation.checkoutTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户数量" align="center" prop="hsReservation.numberOfGuests" />
      <el-table-column label="房间数量" align="center" prop="hsReservation.numberOfRooms" />
      <el-table-column label="订单创建/结束时间" align="center" prop="hsReservation.reservationTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.hsReservation.reservationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="hsReservation.requests" />
      <el-table-column label="总价格" align="center" prop="hsReservation.totalPrice" />
      <el-table-column label="是否支付" align="center" prop="hsReservation.pay" />
      <el-table-column label="订单状态" align="center" prop="hsReservation.reservationStatus" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
              size="mini"
              type="text"
              icon="edit"
              @click="handleUpdate(scope.row.hsReservation)"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="delete"
              @click="handleDelete(scope.row.hsReservation)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改民宿预订对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户联系方式" prop="contactInformation">
          <el-input v-model="form.contactInformation" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="入住时间" prop="checkinTime">
          <el-date-picker clearable
                          v-model="form.checkinTime"
                          type="date"
                          placeholder="请选择入住时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="退房时间" prop="checkoutTime">
          <el-date-picker clearable
                          v-model="form.checkoutTime"
                          type="date"
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
  </div>
</template>

<script>
import { listReservation, getReservation, delReservation, addReservation, updateReservation } from "@/api/homestay/reservation";
import {parseTime} from "../../utils/ruoyi.js";

export default {
  name: "Reservation",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 民宿预订表格数据
      reservationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
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
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    parseTime,
    /** 查询民宿预订列表 */
    getList() {
      this.loading = true;
      listReservation(this.queryParams).then(response => {
        this.reservationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
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
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.checkinTime = this.formatDate(new Date(this.queryParams.checkinTime))
      this.queryParams.checkoutTime = this.formatDate(new Date(this.queryParams.checkoutTime))
      this.queryParams.reservationTime = this.formatDate(new Date(this.queryParams.reservationTime))
      this.queryParams.pageNum = 1;
      this.getList();
    },
    formatDate(date){
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加民宿预订";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      console.log(id)
      getReservation(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改民宿预订";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.checkinTime = this.formatDate(new Date(this.form.checkinTime))
      this.form.checkoutTime = this.formatDate(new Date(this.form.checkoutTime))
      this.form.reservationTime = this.formatDate(new Date(this.form.reservationTime))
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateReservation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReservation(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除民宿预订编号为"' + ids + '"的数据项？').then(function() {
        return delReservation(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('homestay/reservation/export', {
        ...this.queryParams
      }, `reservation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
