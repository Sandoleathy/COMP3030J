<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="栋类型" prop="buildingType">
        <el-input
          v-model="queryParams.buildingType"
          placeholder="请输入栋类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否提供早餐" prop="breakfast">
        <el-input
          v-model="queryParams.breakfast"
          placeholder="请输入是否提供早餐"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否允许抽烟" prop="allowSmoking">
        <el-input
          v-model="queryParams.allowSmoking"
          placeholder="请输入是否允许抽烟"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否有浴缸" prop="bathtub">
        <el-input
          v-model="queryParams.bathtub"
          placeholder="请输入是否有浴缸"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否有淋浴设施" prop="bathroomAmenities">
        <el-input
          v-model="queryParams.bathroomAmenities"
          placeholder="请输入是否有淋浴设施"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否有功能性设施" prop="functionalAmenities">
        <el-input
          v-model="queryParams.functionalAmenities"
          placeholder="请输入是否有功能性设施"
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
          v-hasPermi="['homestay:buildingType:add']"
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
          v-hasPermi="['homestay:buildingType:edit']"
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
          v-hasPermi="['homestay:buildingType:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['homestay:buildingType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="buildingTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="栋类型" align="center" prop="buildingType" />
      <el-table-column label="是否提供早餐" align="center" prop="breakfast" />
      <el-table-column label="是否允许抽烟" align="center" prop="allowSmoking" />
      <el-table-column label="是否有浴缸" align="center" prop="bathtub" />
      <el-table-column label="是否有淋浴设施" align="center" prop="bathroomAmenities" />
      <el-table-column label="是否有功能性设施" align="center" prop="functionalAmenities" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['homestay:buildingType:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['homestay:buildingType:remove']"
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

    <!-- 添加或修改民宿栋类型对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="栋类型" prop="buildingType">
          <el-input v-model="form.buildingType" placeholder="请输入栋类型" />
        </el-form-item>
        <el-form-item label="是否提供早餐" prop="breakfast">
          <el-input v-model="form.breakfast" placeholder="请输入是否提供早餐" />
        </el-form-item>
        <el-form-item label="是否允许抽烟" prop="allowSmoking">
          <el-input v-model="form.allowSmoking" placeholder="请输入是否允许抽烟" />
        </el-form-item>
        <el-form-item label="是否有浴缸" prop="bathtub">
          <el-input v-model="form.bathtub" placeholder="请输入是否有浴缸" />
        </el-form-item>
        <el-form-item label="是否有淋浴设施" prop="bathroomAmenities">
          <el-input v-model="form.bathroomAmenities" placeholder="请输入是否有淋浴设施" />
        </el-form-item>
        <el-form-item label="是否有功能性设施" prop="functionalAmenities">
          <el-input v-model="form.functionalAmenities" placeholder="请输入是否有功能性设施" />
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
import { listBuildingType, getBuildingType, delBuildingType, addBuildingType, updateBuildingType } from "@/api/homestay/buildingType";

export default {
  name: "BuildingType",
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
      // 民宿栋类型表格数据
      buildingTypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        buildingType: null,
        breakfast: null,
        allowSmoking: null,
        bathtub: null,
        bathroomAmenities: null,
        functionalAmenities: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        buildingType: [
          { required: true, message: "栋类型不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询民宿栋类型列表 */
    getList() {
      this.loading = true;
      listBuildingType(this.queryParams).then(response => {
        this.buildingTypeList = response.rows;
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
        buildingType: null,
        breakfast: null,
        allowSmoking: null,
        bathtub: null,
        bathroomAmenities: null,
        functionalAmenities: null
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
      this.title = "添加民宿栋类型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBuildingType(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改民宿栋类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBuildingType(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBuildingType(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除民宿栋类型编号为"' + ids + '"的数据项？').then(function() {
        return delBuildingType(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('homestay/buildingType/export', {
        ...this.queryParams
      }, `buildingType_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
