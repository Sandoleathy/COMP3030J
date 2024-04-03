<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="房间id" prop="roomId">
        <el-input
          v-model="queryParams.roomId"
          placeholder="请输入房间id"
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
      <el-form-item label="房间数" prop="numberOfRooms">
        <el-input
          v-model="queryParams.numberOfRooms"
          placeholder="请输入房间数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户数" prop="numberOfGuests">
        <el-input
          v-model="queryParams.numberOfGuests"
          placeholder="请输入客户数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预订状态" prop="reservationStatus">
        <el-input
          v-model="queryParams.reservationStatus"
          placeholder="请输入预订状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预订时间" prop="reservationTime">
        <el-date-picker clearable
          v-model="queryParams.reservationTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预订时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="checkoutTime">
        <el-date-picker clearable
          v-model="queryParams.checkoutTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
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
      <el-table-column label="" align="center" prop="id" />
      <el-table-column label="客户联系方式" align="center" prop="contactInformation" />
      <el-table-column label="房间id" align="center" prop="roomId" />
      <el-table-column label="入住时间" align="center" prop="checkinTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkinTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="房间数" align="center" prop="numberOfRooms" />
      <el-table-column label="客户数" align="center" prop="numberOfGuests" />
      <el-table-column label="预订状态" align="center" prop="reservationStatus" />
      <el-table-column label="预订时间" align="center" prop="reservationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户要求" align="center" prop="requests" />
      <el-table-column label="" align="center" prop="checkoutTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkoutTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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
        <el-form-item label="房间id" prop="roomId">
          <el-input v-model="form.roomId" placeholder="请输入房间id" />
        </el-form-item>
        <el-form-item label="入住时间" prop="checkinTime">
          <el-date-picker clearable
            v-model="form.checkinTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择入住时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="房间数" prop="numberOfRooms">
          <el-input v-model="form.numberOfRooms" placeholder="请输入房间数" />
        </el-form-item>
        <el-form-item label="客户数" prop="numberOfGuests">
          <el-input v-model="form.numberOfGuests" placeholder="请输入客户数" />
        </el-form-item>
        <el-form-item label="预订状态" prop="reservationStatus">
          <el-input v-model="form.reservationStatus" placeholder="请输入预订状态" />
        </el-form-item>
        <el-form-item label="预订时间" prop="reservationTime">
          <el-date-picker clearable
            v-model="form.reservationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预订时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客户要求" prop="requests">
          <el-input v-model="form.requests" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="" prop="checkoutTime">
          <el-date-picker clearable
            v-model="form.checkoutTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-divider content-position="center">民宿房间信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddHsRoom">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteHsRoom">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="hsRoomList" :row-class-name="rowHsRoomIndex" @selection-change="handleHsRoomSelectionChange" ref="hsRoom">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="房间号" prop="roomNumber" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.roomNumber" placeholder="请输入房间号" />
            </template>
          </el-table-column>
          <el-table-column label="房间类型" prop="roomType" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.roomType" placeholder="请输入房间类型" />
            </template>
          </el-table-column>
          <el-table-column label="床类型" prop="bedType" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bedType" placeholder="请输入床类型" />
            </template>
          </el-table-column>
          <el-table-column label="价格" prop="roomPrice" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.roomPrice" placeholder="请输入价格" />
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="roomStatus" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.roomStatus" placeholder="请输入状态" />
            </template>
          </el-table-column>
          <el-table-column label="最大人数" prop="maxOccupancy" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.maxOccupancy" placeholder="请输入最大人数" />
            </template>
          </el-table-column>
          <el-table-column label="入住时间" prop="checkIntTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.checkIntTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择入住时间" />
            </template>
          </el-table-column>
          <el-table-column label="搬出时间" prop="checkOutTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.checkOutTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择搬出时间" />
            </template>
          </el-table-column>
        </el-table>
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
      // 子表选中数据
      checkedHsRoom: [],
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
      // 民宿房间表格数据
      hsRoomList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        contactInformation: null,
        roomId: null,
        checkinTime: null,
        numberOfRooms: null,
        numberOfGuests: null,
        reservationStatus: null,
        reservationTime: null,
        requests: null,
        checkoutTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        roomId: [
          { required: true, message: "房间id不能为空", trigger: "blur" }
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
        roomId: null,
        checkinTime: null,
        numberOfRooms: null,
        numberOfGuests: null,
        reservationStatus: null,
        reservationTime: null,
        requests: null,
        checkoutTime: null
      };
      this.hsRoomList = [];
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
        this.hsRoomList = response.data.hsRoomList;
        this.open = true;
        this.title = "修改民宿预订";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.hsRoomList = this.hsRoomList;
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
	/** 民宿房间序号 */
    rowHsRoomIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 民宿房间添加按钮操作 */
    handleAddHsRoom() {
      let obj = {};
      obj.roomNumber = "";
      obj.roomType = "";
      obj.bedType = "";
      obj.roomPrice = "";
      obj.roomStatus = "";
      obj.maxOccupancy = "";
      obj.amenities = "";
      obj.roomDescription = "";
      obj.checkIntTime = "";
      obj.checkOutTime = "";
      obj.customerDemand = "";
      this.hsRoomList.push(obj);
    },
    /** 民宿房间删除按钮操作 */
    handleDeleteHsRoom() {
      if (this.checkedHsRoom.length == 0) {
        this.$modal.msgError("请先选择要删除的民宿房间数据");
      } else {
        const hsRoomList = this.hsRoomList;
        const checkedHsRoom = this.checkedHsRoom;
        this.hsRoomList = hsRoomList.filter(function(item) {
          return checkedHsRoom.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleHsRoomSelectionChange(selection) {
      this.checkedHsRoom = selection.map(item => item.index)
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
