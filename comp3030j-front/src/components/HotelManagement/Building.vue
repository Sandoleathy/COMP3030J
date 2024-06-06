<script setup>
import { listBuildingType, getBuildingType, delBuildingType, addBuildingType, updateBuildingType } from "@/api/homestay/buildingType";
import { onMounted, ref } from "vue";
import {ElMessage, ElMessageBox} from "element-plus";

const { proxy } = getCurrentInstance();
onMounted(() => {
  getList()
})

const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  buildingType: null,
  breakfast: null,
  allowSmoking: null,
  bathtub: null,
  bathroomAmenities: null,
  functionalAmenities: null
})

const buildingTypeList = ref([])
const showSearch = ref(true)
const loading = ref(false)
const single = ref(true)
const multiple = ref(false)
const total = ref(0)
const open = ref(false)
const title = ref("")
const form = ref({
  id: null,
  buildingType: null,
  breakfast: null,
  allowSmoking: null,
  bathtub: null,
  bathroomAmenities: null,
  functionalAmenities: null
})
const rules = ref({
  buildingType: [
    { required: true, message: "The building type cannot be empty", trigger: "blur" }
  ],
})

const handleQuery = () => {
  queryParams.value.pageNum = 1;
  getList();
}
const resetQuery = () => {
  this.resetForm("queryForm");
  handleQuery();
}
const handleUpdate = (row) => {
  reset();
  const id = row.id || ids.value
  getBuildingType(id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改民宿栋类型";
  });
}
const getList = () => {
  loading.value = true;
  listBuildingType(queryParams.value).then(response => {
    buildingTypeList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}
const handleSelectionChange = (selection) => {
  ids.value = selection.map(item => item.id)
  single.value = selection.length!==1
  multiple.value = !selection.length
}
const cancel = () => {
  open.value = false;
  reset();
}
const reset = () => {
  form.value = {
    id: null,
    buildingType: null,
    breakfast: null,
    allowSmoking: null,
    bathtub: null,
    bathroomAmenities: null,
    functionalAmenities: null
  };
  proxy.resetForm("form");
}
const submitForm = () => {

}
</script>

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
        <el-button type="primary" icon="el-icon-search" size="small" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="small" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

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
        <template v-slot="scope">
          <el-button
              size="small"
              type="text"
              icon="edit"
              @click="handleUpdate(scope.row)"
          >修改</el-button>
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
    <el-dialog :title="title" v-model="open" width="60vw">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="栋类型" prop="buildingType" label-width="15%">
          <el-input v-model="form.buildingType" placeholder="请输入栋类型" />
        </el-form-item>
        <el-form-item label="是否提供早餐" prop="breakfast" label-width="15%">
          <el-input v-model="form.breakfast" placeholder="请输入是否提供早餐" />
        </el-form-item>
        <el-form-item label="是否允许抽烟" prop="allowSmoking" label-width="15%">
          <el-input v-model="form.allowSmoking" placeholder="请输入是否允许抽烟" />
        </el-form-item>
        <el-form-item label="是否有浴缸" prop="bathtub" label-width="15%">
          <el-input v-model="form.bathtub" placeholder="请输入是否有浴缸" />
        </el-form-item>
        <el-form-item label="是否有淋浴设施" prop="bathroomAmenities" label-width="15%">
          <el-input v-model="form.bathroomAmenities" placeholder="请输入是否有淋浴设施" />
        </el-form-item>
        <el-form-item label="是否有功能性设施" prop="functionalAmenities" label-width="15%">
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

<style scoped>

</style>
