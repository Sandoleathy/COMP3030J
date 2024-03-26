<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="contactInformation">
        <el-input
          v-model="queryParams.contactInformation"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="roomNumber">
        <el-input
          v-model="queryParams.roomNumber"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="checkinDate">
        <el-date-picker clearable
          v-model="queryParams.checkinDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="checkoutDate">
        <el-date-picker clearable
          v-model="queryParams.checkoutDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="numberOfRooms">
        <el-input
          v-model="queryParams.numberOfRooms"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="numberOfGuests">
        <el-input
          v-model="queryParams.numberOfGuests"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="reservationTime">
        <el-date-picker clearable
          v-model="queryParams.reservationTime"
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
      <el-table-column label="" align="center" prop="contactInformation" />
      <el-table-column label="" align="center" prop="roomNumber" />
      <el-table-column label="" align="center" prop="checkinDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkinDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" prop="checkoutDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkoutDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" prop="numberOfRooms" />
      <el-table-column label="" align="center" prop="numberOfGuests" />
      <el-table-column label="" align="center" prop="reservationStatus" />
      <el-table-column label="" align="center" prop="reservationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" prop="specialRequests" />
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

    <!-- 添加或修改房间预定对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="" prop="contactInformation">
          <el-input v-model="form.contactInformation" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="roomNumber">
          <el-input v-model="form.roomNumber" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="checkinDate">
          <el-date-picker clearable
            v-model="form.checkinDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="checkoutDate">
          <el-date-picker clearable
            v-model="form.checkoutDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="numberOfRooms">
          <el-input v-model="form.numberOfRooms" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="numberOfGuests">
          <el-input v-model="form.numberOfGuests" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="reservationTime">
          <el-date-picker clearable
            v-model="form.reservationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="specialRequests">
          <el-input v-model="form.specialRequests" type="textarea" placeholder="请输入内容" />
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
          <el-table-column label="" prop="roomType" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.roomType" placeholder="请选择">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="" prop="bedType" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.bedType" placeholder="请选择">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="" prop="roomPrice" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.roomPrice" placeholder="请输入" />
            </template>
          </el-table-column>
          <el-table-column label="" prop="roomStatus" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.roomStatus" placeholder="请选择">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="" prop="maxOccupancy" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.maxOccupancy" placeholder="请输入" />
            </template>
          </el-table-column>
          <el-table-column label="" prop="checkInTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.checkInTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择" />
            </template>
          </el-table-column>
          <el-table-column label="" prop="checkOutTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.checkOutTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择" />
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
      // 房间预定表格数据
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
        roomNumber: null,
        checkinDate: null,
        checkoutDate: null,
        numberOfRooms: null,
        numberOfGuests: null,
        reservationStatus: null,
        reservationTime: null,
        specialRequests: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        roomNumber: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        checkinDate: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        checkoutDate: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        numberOfRooms: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        numberOfGuests: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询房间预定列表 */
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
        checkinDate: null,
        checkoutDate: null,
        numberOfRooms: null,
        numberOfGuests: null,
        reservationStatus: null,
        reservationTime: null,
        specialRequests: null
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
      this.title = "添加房间预定";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getReservation(id).then(response => {
        this.form = response.data;
        this.hsRoomList = response.data.hsRoomList;
        this.open = true;
        this.title = "修改房间预定";
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
      this.$modal.confirm('是否确认删除房间预定编号为"' + ids + '"的数据项？').then(function() {
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
      obj.roomType = "";
      obj.bedType = "";
      obj.roomPrice = "";
      obj.roomStatus = "";
      obj.maxOccupancy = "";
      obj.amenities = "";
      obj.roomDescription = "";
      obj.checkInTime = "";
      obj.checkOutTime = "";
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
