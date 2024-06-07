<script setup>
import { listBuildingType, getBuildingType, delBuildingType, addBuildingType, updateBuildingType } from "@/api/homestay/buildingType";
import { onMounted, ref, reactive } from "vue";
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
const dialogForm = ref({
  id: null,
  buildingType: null,
  breakfast: null,
  allowSmoking: null,
  bathtub: null,
  bathroomAmenities: null,
  functionalAmenities: null
})

const ids = ref([])
const buildingTypeList = ref([])
const showSearch = ref(true)
const loading = ref(false)
const single = ref(true)
const multiple = ref(false)
const total = ref(0)
const open = ref(false)
const title = ref("")
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
  queryParams.value = {
    pageNum: 1,
    pageSize: 10,
    buildingType: null,
    breakfast: null,
    allowSmoking: null,
    bathtub: null,
    bathroomAmenities: null,
    functionalAmenities: null
  }
  //this.resetForm("queryForm");
  handleQuery();
}

const selectedID = ref(0)
const handleUpdate = (row) => {
  selectedID.value = -1
  reset();
  const id = row.id || this.ids.value
  getBuildingType(id).then(response => {
    //dialogForm.value = response.data;
    open.value = true;
    title.value = "修改民宿栋类型";
    console.log("get buliding type after")
    console.log(dialogForm.value)
    console.log(queryParams.value)
    selectedID.value = id
  });
}
const getList = () => {
  loading.value = true;
  listBuildingType(queryParams.value).then(response => {
    buildingTypeList.value = response.rows;
    total.value = response.total;
    loading.value = false;
    console.log(response)
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
  dialogForm.value = {
    id: null,
    buildingType: null,
    breakfast: null,
    allowSmoking: null,
    bathtub: null,
    bathroomAmenities: null,
    functionalAmenities: null
  };
  buildingType.value = ""
  breakfast.value = false
  allowSmoking.value = false
  bathtub.value = false
  bathroomAmenities.value = false
  functionalAmenities.value = false
}
const submitForm = () => {
  dialogForm.value = {
    id: selectedID.value,
    buildingType: buildingType.value ? 1 : 0,
    breakfast: breakfast.value ? 1 : 0,
    allowSmoking: allowSmoking.value ? 1 : 0,
    bathtub: bathtub.value ? 1 : 0,
    bathroomAmenities: bathroomAmenities.value ? 1 : 0,
    functionalAmenities: functionalAmenities.value ? 1 : 0
  };

  if (dialogForm.value.id != null) {
    updateBuildingType(dialogForm.value).then(response => {
      ElMessage.success("11")
      this.open = false;
      getList();
    });
  }
  //console.log(dialogForm.value)
}

const buildingType = ref("")
const breakfast = ref(false)
const allowSmoking = ref(false)
const bathtub = ref(false)
const bathroomAmenities = ref(false)
const functionalAmenities = ref(false)
</script>

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="栋类型" prop="buildingType" label-width="150px">
            <el-input
                v-model="queryParams.buildingType"
                placeholder="请输入栋类型"
                clearable
                @keyup.enter.native="handleQuery"
                style="width: 200px"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否提供早餐" prop="breakfast" label-width="150px">
            <el-select v-model="queryParams.breakfast" placeholder="请选择是否提供早餐" style="width: 200px">
              <el-option label="是" :value="1" />
              <el-option label="否" :value="0" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否允许抽烟" prop="allowSmoking" label-width="150px">
            <el-select v-model="queryParams.allowSmoking" placeholder="请选择是否允许抽烟" style="width: 200px">
              <el-option label="是" :value="1" />
              <el-option label="否" :value="0" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="是否有浴缸" prop="bathtub" label-width="150px">
            <el-select v-model="queryParams.bathtub" placeholder="请选择是否有浴缸" style="width: 200px">
              <el-option label="是" :value="1" />
              <el-option label="否" :value="0" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否有淋浴设施" prop="bathroomAmenities" label-width="150px">
            <el-select v-model="queryParams.bathroomAmenities" placeholder="请选择是否有淋浴设施" style="width: 200px">
              <el-option label="是" :value="1" />
              <el-option label="否" :value="0" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="是否有功能性设施" prop="functionalAmenities" label-width="150px">
            <el-select v-model="queryParams.functionalAmenities" placeholder="请选择是否有功能性设施" style="width: 200px">
              <el-option label="是" :value="1" />
              <el-option label="否" :value="0" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>





      <el-form-item>
        <el-button type="primary" icon="search" size="small" @click="handleQuery">搜索</el-button>
        <el-button icon="refresh" size="small" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="buildingTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="栋类型" align="center" prop="buildingType" />
      <el-table-column label="是否提供早餐" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.breakfast ? '√' : '×' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否允许抽烟" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.allowSmoking ? '√' : '×' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否有浴缸" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.bathtub ? '√' : '×' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否有淋浴设施" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.bathroomAmenities ? '√' : '×' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否有功能性设施" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.functionalAmenities ? '√' : '×' }}</span>
        </template>
      </el-table-column>
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
      <el-form ref="dialogForm" label-width="80px">
        <el-form-item label="栋类型" prop="buildingType" label-width="15%">
          <el-input v-model="buildingType" placeholder="请输入栋类型" minlength="1"/>
        </el-form-item>
        <el-form-item label="是否提供早餐" prop="breakfast" label-width="15%">
          <el-select v-model="breakfast" placeholder="是否允许抽烟">
            <el-option label="是" :value="true" />
            <el-option label="否" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否允许抽烟" prop="allowSmoking" label-width="15%">
          <el-select v-model="allowSmoking" placeholder="是否允许抽烟">
            <el-option label="是" :value="true" />
            <el-option label="否" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否有浴缸" prop="bathtub" label-width="15%">
          <el-select v-model="bathtub" placeholder="是否有浴缸">
            <el-option label="是" :value="true" />
            <el-option label="否" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否有淋浴设施" prop="bathroomAmenities" label-width="15%">
          <el-select v-model="bathroomAmenities" placeholder="是否有淋浴设施">
            <el-option label="是" :value="true" />
            <el-option label="否" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否有功能性设施" prop="functionalAmenities" label-width="15%">
          <el-select v-model="functionalAmenities" placeholder="请选择是否有功能性设施">
            <el-option label="是" :value="true" />
            <el-option label="否" :value="false" />
          </el-select>
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
