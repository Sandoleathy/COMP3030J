<script setup>
import { listBuildingType, getBuildingType, delBuildingType, addBuildingType, updateBuildingType } from "@/api/homestay/buildingType";
import { onMounted, ref, reactive } from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import { useI18n } from 'vue-i18n';
const { t } = useI18n();

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
    title.value = t('building.modifybuilding');
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
const getBuildingTypeText = (type) => {
    switch (type) {
        case '1':
            return t('roonItems.economic');
        case '2':
            return t('roonItems.deluxe');
        case '3':
            return t('roonItems.family');
        case '4':
            return t('roonItems.mountain');
        default:
            return t('roonItems.unknown');
    }
};
</script>

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-row>
        <el-col :span="8">
          <el-form-item :label="t('building.buildingtype')" prop="buildingType" label-width="150px">
            <el-input
                v-model="queryParams.buildingType"
                :placeholder="t('building.enterbuilding')"
                clearable
                @keyup.enter.native="handleQuery"
                style="width: 200px"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item :label="t('building.breakfast')" prop="breakfast" label-width="150px">
            <el-select v-model="queryParams.breakfast" :placeholder="t('building.choosebreakfast')" style="width: 200px">
              <el-option :label="t('building.yes')" :value="1" />
              <el-option :label="t('building.no')" :value="0" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item :label="t('building.smoking')" prop="allowSmoking" label-width="150px">
            <el-select v-model="queryParams.allowSmoking" :placeholder="t('building.choosesmoking')" style="width: 200px">
              <el-option :label="t('building.yes')" :value="1" />
              <el-option :label="t('building.no')" :value="0" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item :label="t('building.bath')" prop="bathtub" label-width="150px">
            <el-select v-model="queryParams.bathtub" :placeholder="t('building.choosebath')" style="width: 200px">
              <el-option :label="t('building.yes')" :value="1" />
              <el-option :label="t('building.no')" :value="0" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item :label="t('building.showerfacilities')" prop="bathroomAmenities" label-width="150px">
            <el-select v-model="queryParams.bathroomAmenities" :placeholder="t('building.chooseshower')" style="width: 200px">
              <el-option :label="t('building.yes')" :value="1" />
              <el-option :label="t('building.no')" :value="0" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item :label="t('building.facility')" prop="functionalAmenities" label-width="150px">
            <el-select v-model="queryParams.functionalAmenities" :placeholder="t('building.choosefacility')" style="width: 200px">
              <el-option :label="t('building.yes')" :value="1" />
              <el-option :label="t('building.no')" :value="0" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" icon="search" size="small" @click="handleQuery">{{t('building.search')}}</el-button>
        <el-button icon="refresh" size="small" @click="resetQuery">{{t('building.reset')}}</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="buildingTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
        <el-table-column :label="t('building.buildingtype')" align="center">
            <template #default="scope">
                {{ getBuildingTypeText(scope.row.buildingType) }}
            </template>
        </el-table-column>
      <el-table-column :label="t('building.breakfast')" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.breakfast ? '√' : '×' }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="t('building.smoking')" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.allowSmoking ? '√' : '×' }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="t('building.bath')" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.bathtub ? '√' : '×' }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="t('building.showerfacilities')"  align="center">
        <template v-slot="scope">
          <span>{{ scope.row.bathroomAmenities ? '√' : '×' }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="t('building.facility')" align="center">
        <template v-slot="scope">
          <span>{{ scope.row.functionalAmenities ? '√' : '×' }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="t('building.operate')" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
              size="small"
              type="text"
              icon="edit"
              @click="handleUpdate(scope.row)"
          >{{t('building.modify')}}</el-button>
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
        <el-form-item :label="t('building.buildingtype')" prop="buildingType" label-width="20%">
          <el-input v-model="buildingType" :placeholder="t('building.enterbuilding')" minlength="1"/>
        </el-form-item>
        <el-form-item :label="t('building.breakfast')" prop="breakfast" label-width="20%">
          <el-select v-model="breakfast" :placeholder="t('building.choosebrakfast')">
            <el-option :label="t('building.yes')" :value="true" />
            <el-option :label="t('building.no')" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item :label="t('building.smoking')" prop="allowSmoking" label-width="20%">
          <el-select v-model="allowSmoking" :placeholder="t('building.choosesmoking')">
            <el-option :label="t('building.yes')" :value="true" />
            <el-option :label="t('building.no')" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item :label="t('building.bath')"  prop="bathtub" label-width="20%">
          <el-select v-model="bathtub" :placeholder="t('building.choosebath')">
            <el-option :label="t('building.yes')" :value="true" />
            <el-option :label="t('building.no')" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item :label="t('building.showerfacilities')"  prop="bathroomAmenities" label-width="20%">
          <el-select v-model="bathroomAmenities" :placeholder="t('building.chooseshower')">
            <el-option :label="t('building.yes')" :value="true" />
            <el-option :label="t('building.no')" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item :label="t('building.facility')" prop="functionalAmenities" label-width="20%">
          <el-select v-model="functionalAmenities" :placeholder="t('building.choosefacility')">
            <el-option :label="t('building.yes')" :value="true" />
            <el-option :label="t('building.no')" :value="false" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">{{t('building.confirm')}}</el-button>
        <el-button @click="cancel">{{t('building.cancel')}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>
