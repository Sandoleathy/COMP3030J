<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item :label="t('Room.roomType')" prop="roomType" label-width="100px">
        <el-input
            v-model="queryParams.roomType"
            :placeholder="t('Room.inputRoomType')"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item :label="t('Room.roomNumber')" prop="roomNumber" label-width="100px">
        <el-input
            v-model="queryParams.roomNumber"
            :placeholder="t('Room.inputRoomNumber')"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item :label="t('Room.buildingType')" prop="buildingType" label-width="100px">
        <el-input
            v-model="queryParams.buildingType"
            :placeholder="t('Room.inputBuildingType')"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item :label="t('Room.bedType')" prop="bedType" label-width="100px">
        <el-input
            v-model="queryParams.bedType"
            :placeholder="t('inputBedType')"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item :label="t('Room.price')" prop="roomPrice" label-width="100px">
        <el-input
            v-model="queryParams.roomPrice"
            :placeholder="t('Room.inputPrice')"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-row>
        <el-form-item>
          <el-button type="primary" icon="search" size="small" @click="handleQuery">{{ t('Room.search') }}</el-button>
          <el-button icon="refresh" size="small" @click="resetQuery">{{ t('Room.reset') }}</el-button>
        </el-form-item>
      </el-row>
    </el-form>



    <el-table v-loading="loading" :data="roomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="hsRoom.id" />
      <el-table-column :label="t('Room.roomType')" align="center" prop="hsRoom.roomType" />
      <el-table-column :label="t('Room.roomNumber')" align="center" prop="hsRoom.roomNumber" />
      <el-table-column :label="t('Room.buildingType')" align="center" prop="hsRoom.buildingType" />
      <el-table-column :label="t('Room.bedType')" align="center" prop="hsRoom.bedType" />
      <el-table-column :label="t('Room.price')" align="center" prop="hsRoom.roomPrice" />
      <el-table-column :label="t('Room.operation')" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
              size="mini"
              type="text"
              icon="edit"
              @click="handleUpdate(scope.row.hsRoom)"
          >{{ t('Room.edit') }}</el-button>
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
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item :label="t('Room.roomType')" prop="roomType">
          <el-input v-model="form.roomType" :placeholder="t('Room.inputRoomType')" />
        </el-form-item>
        <el-form-item :label="t('Room.roomNumber')" prop="roomNumber">
          <el-input v-model="form.roomNumber" :placeholder="t('Room.inputRoomNumber')" />
        </el-form-item>
        <el-form-item :label="t('Room.buildingType')" prop="buildingType">
          <el-input v-model="form.buildingType" :placeholder="t('Room.inputBuildingType')" />
        </el-form-item>
        <el-form-item :label="t('Room.bedType')" prop="bedType">
          <el-input v-model="form.bedType" :placeholder="t('inputBedType')" />
        </el-form-item>
        <el-form-item :label="t('Room.price')" prop="roomPrice">
          <el-input v-model="form.roomPrice" :placeholder="t('Room.inputPrice')" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">{{ t('Room.confirm') }}</el-button>
        <el-button @click="cancel">{{ t('Room.cancel') }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRoom, getRoom, delRoom, addRoom, updateRoom } from "@/api/homestay/room";
import { useI18n } from 'vue-i18n';

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
        roomType: null,
        roomNumber: null,
        buildingType: null,
        bedType: null,
        roomPrice: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        buildingType: [
          { required: true, message: this.t('Room.buildingIsEmpty'), trigger: "blur" }
        ],
        bedType: [
          { required: true, message: this.t('Room.bedIsEmpty'), trigger: "blur" }
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
        console.log(this.roomList)
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
        roomType: null,
        roomNumber: null,
        buildingType: null,
        bedType: null,
        roomPrice: null
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
      this.title = this.t('Room.addRoom');
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      //console.log(row.id)
      getRoom(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = this.t('Room.editRoom');
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRoom(this.form).then(response => {
              this.$modal.msgSuccess(this.t('Room.editSuccess'));
              this.open = false;
              this.getList();
            });
          } else {
            addRoom(this.form).then(response => {
              this.$modal.msgSuccess(this.t('Room.addSuccess'));
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
  },
  setup() {
    const { t } = useI18n();
    return { t };
  }
};
</script>
