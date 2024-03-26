<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="roomNumber">
        <el-input
          v-model="queryParams.roomNumber"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="roomPrice">
        <el-input
          v-model="queryParams.roomPrice"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="maxOccupancy">
        <el-input
          v-model="queryParams.maxOccupancy"
          placeholder="请输入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="checkInTime">
        <el-date-picker clearable
          v-model="queryParams.checkInTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="checkOutTime">
        <el-date-picker clearable
          v-model="queryParams.checkOutTime"
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
          v-hasPermi="['homestay:room:add']"
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
          v-hasPermi="['homestay:room:edit']"
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
          v-hasPermi="['homestay:room:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['homestay:room:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="roomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="id" />
      <el-table-column label="" align="center" prop="roomNumber" />
      <el-table-column label="" align="center" prop="roomType" />
      <el-table-column label="" align="center" prop="bedType" />
      <el-table-column label="" align="center" prop="roomPrice" />
      <el-table-column label="" align="center" prop="roomStatus" />
      <el-table-column label="" align="center" prop="maxOccupancy" />
      <el-table-column label="" align="center" prop="amenities" />
      <el-table-column label="" align="center" prop="roomDescription" />
      <el-table-column label="" align="center" prop="checkInTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkInTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" prop="checkOutTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkOutTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改民宿房间对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="" prop="roomPrice">
          <el-input v-model="form.roomPrice" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="maxOccupancy">
          <el-input v-model="form.maxOccupancy" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="amenities">
          <el-input v-model="form.amenities" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="" prop="roomDescription">
          <el-input v-model="form.roomDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="" prop="checkInTime">
          <el-date-picker clearable
            v-model="form.checkInTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="checkOutTime">
          <el-date-picker clearable
            v-model="form.checkOutTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择">
          </el-date-picker>
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
import { listRoom, getRoom, delRoom, addRoom, updateRoom } from "@/api/homestay/room";

export default {
  name: "Room",
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
      // 民宿房间表格数据
      roomList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roomNumber: null,
        roomType: null,
        bedType: null,
        roomPrice: null,
        roomStatus: null,
        maxOccupancy: null,
        amenities: null,
        roomDescription: null,
        checkInTime: null,
        checkOutTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        roomNumber: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        bedType: [
          { required: true, message: "不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询民宿房间列表 */
    getList() {
      this.loading = true;
      listRoom(this.queryParams).then(response => {
        this.roomList = response.rows;
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
        roomNumber: null,
        roomType: null,
        bedType: null,
        roomPrice: null,
        roomStatus: null,
        maxOccupancy: null,
        amenities: null,
        roomDescription: null,
        checkInTime: null,
        checkOutTime: null
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
      this.title = "添加民宿房间";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRoom(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改民宿房间";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRoom(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRoom(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除民宿房间编号为"' + ids + '"的数据项？').then(function() {
        return delRoom(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('homestay/room/export', {
        ...this.queryParams
      }, `room_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
