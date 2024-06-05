<template>
   <!-- authorized user -->
   <el-dialog title="Select user" v-model="visible" width="800px" top="5vh" append-to-body>
      <el-form :model="queryParams" ref="queryRef" :inline="true">
         <el-form-item label="user name" prop="userName">
            <el-input
               v-model="queryParams.userName"
               placeholder="Please enter username"
               clearable
               style="width: 200px"
               @keyup.enter="handleQuery"
            />
         </el-form-item>
         <el-form-item label="phone number" prop="phonenumber">
            <el-input
               v-model="queryParams.phonenumber"
               placeholder="Please enter the phone number"
               clearable
               style="width: 200px"
               @keyup.enter="handleQuery"
            />
         </el-form-item>
         <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">search</el-button>
            <el-button icon="Refresh" @click="resetQuery">reset</el-button>
         </el-form-item>
      </el-form>
      <el-row>
         <el-table @row-click="clickRow" ref="refTable" :data="userList" @selection-change="handleSelectionChange" height="260px">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column label="user name" prop="userName" :show-overflow-tooltip="true" />
            <el-table-column label="User's Nickname" prop="nickName" :show-overflow-tooltip="true" />
            <el-table-column label="Mail" prop="email" :show-overflow-tooltip="true" />
            <el-table-column label="cell phone" prop="phonenumber" :show-overflow-tooltip="true" />
            <el-table-column label="state" align="center" prop="status">
               <template #default="scope">
                  <dict-tag :options="sys_normal_disable" :value="scope.row.status" />
               </template>
            </el-table-column>
            <el-table-column label="creation time" align="center" prop="createTime" width="180">
               <template #default="scope">
                  <span>{{ parseTime(scope.row.createTime) }}</span>
               </template>
            </el-table-column>
         </el-table>
         <pagination
            v-show="total > 0"
            :total="total"
            v-model:page="queryParams.pageNum"
            v-model:limit="queryParams.pageSize"
            @pagination="getList"
         />
      </el-row>
      <template #footer>
         <div class="dialog-footer">
            <el-button type="primary" @click="handleSelectUser">Confirm</el-button>
            <el-button @click="visible = false">Cancel</el-button>
         </div>
      </template>
   </el-dialog>
</template>

<script setup name="SelectUser">
import { authUserSelectAll, unallocatedUserList } from "@/api/system/role";

const props = defineProps({
  roleId: {
    type: [Number, String]
  }
});

const { proxy } = getCurrentInstance();
const { sys_normal_disable } = proxy.useDict("sys_normal_disable");

const userList = ref([]);
const visible = ref(false);
const total = ref(0);
const userIds = ref([]);

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  roleId: undefined,
  userName: undefined,
  phonenumber: undefined
});

// 显示弹框
function show() {
  queryParams.roleId = props.roleId;
  getList();
  visible.value = true;
}
/**选择行 */
function clickRow(row) {
  proxy.$refs["refTable"].toggleRowSelection(row);
}
// 多选框选中数据
function handleSelectionChange(selection) {
  userIds.value = selection.map(item => item.userId);
}
// 查询表数据
function getList() {
  unallocatedUserList(queryParams).then(res => {
    userList.value = res.rows;
    total.value = res.total;
  });
}
/** search按钮操作 */
function handleQuery() {
  queryParams.pageNum = 1;
  getList();
}
/** reset按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}
const emit = defineEmits(["ok"]);
/** 选择authorized user操作 */
function handleSelectUser() {
  const roleId = queryParams.roleId;
  const uIds = userIds.value.join(",");
  if (uIds == "") {
    proxy.$modal.msgError("Please select a user to assign");
    return;
  }
  authUserSelectAll({ roleId: roleId, userIds: uIds }).then(res => {
    proxy.$modal.msgSuccess(res.msg);
    if (res.code === 200) {
      visible.value = false;
      emit("ok");
    }
  });
}

defineExpose({
  show,
});
</script>
