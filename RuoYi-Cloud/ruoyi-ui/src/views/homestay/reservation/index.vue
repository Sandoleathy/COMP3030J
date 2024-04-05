<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="房间号" prop="roomNumber">
        <el-input
          v-model="queryParams.roomNumber"
          placeholder="请输入房间号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['homestay:reservation:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['homestay:reservation:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['homestay:reservation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['homestay:reservation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reservationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="客户联系方式" align="center" prop="contactInformation" />
      <el-table-column label="房间号" align="center" prop="roomNumber" />
      <el-table-column label="入住时间" align="center" prop="checkinTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkinTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="退房时间" align="center" prop="checkoutTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkoutTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户数量" align="center" prop="numberOfGuests" />
      <el-table-column label="订单状态" align="center" prop="reservationStatus" />
      <el-table-column label="订单创建/结束时间" align="center" prop="reservationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户要求" align="center" prop="requests" />
      <el-table-column label="总价格" align="center" prop="totalPrice" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['homestay:reservation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['homestay:reservation:remove']"
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
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户联系方式" prop="contactInformation">
          <el-input v-model="form.contactInformation" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="房间号" prop="roomNumber">
          <el-input v-model="form.roomNumber" placeholder="请输入房间号" />
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
        <el-form-item label="订单状态" prop="reservationStatus">
          <el-input v-model="form.reservationStatus" placeholder="请输入订单状态" />
        </el-form-item>
        <el-form-item label="订单创建/结束时间" prop="reservationTime">
          <el-date-picker clearable
            v-model="form.reservationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择订单创建/结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客户要求" prop="requests">
          <el-input v-model="form.requests" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="总价格" prop="totalPrice">
          <el-input v-model="form.totalPrice" placeholder="请输入总价格" />
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
        roomNumber: null,
        checkinTime: null,
        checkoutTime: null,
        numberOfGuests: null,
        reservationStatus: null,
        reservationTime: null,
        requests: null,
        totalPrice: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        roomNumber: [
          { required: true, message: "房间号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
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
        roomNumber: null,
        checkinTime: null,
        checkoutTime: null,
        numberOfGuests: null,
        reservationStatus: null,
        reservationTime: null,
        requests: null,
        totalPrice: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
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
      getReservation(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改民宿预订";
      });
    },
    /** 提交按钮 */
    submitForm() {
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
