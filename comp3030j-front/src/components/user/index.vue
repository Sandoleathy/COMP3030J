<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!--Department data-->
         <el-col :span="4" :xs="24">
            <div class="head-container">
               <el-input
                  v-model="deptName"
                  :placeholder="t('user.enterDepartmentName')"
                  clearable
                  prefix-icon="Search"
                  style="margin-bottom: 20px"
               />
            </div>
            <div class="head-container">
               <el-tree
                  :data="deptOptions"
                  :props="{ label: 'label', children: 'children' }"
                  :expand-on-click-node="false"
                  :filter-node-method="filterNode"
                  ref="deptTreeRef"
                  node-key="id"
                  highlight-current
                  default-expand-all
                  @node-click="handleNodeClick"
               />
            </div>
         </el-col>
         <!--User data-->
         <el-col :span="20" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="110px">
               <el-form-item :label="t('user.userName')" prop="userName">
                  <el-input
                     v-model="queryParams.userName"
                     :placeholder="t('user.enterUserName')"
                     clearable
                     style="width: 240px"
                     @keyup.enter="handleQuery"
                  />
               </el-form-item>
               <el-form-item :label="t('user.phoneNumber')" prop="phonenumber">
                  <el-input
                     v-model="queryParams.phonenumber"
                     :placeholder="t('user.enterPhoneNumber')"
                     clearable
                     style="width: 240px"
                     @keyup.enter="handleQuery"
                  />
               </el-form-item>
               <el-form-item :label="t('user.status')" prop="status">
                  <el-select
                     v-model="queryParams.status"
                     :placeholder="t('user.userStatus')"
                     clearable
                     style="width: 240px"
                  >
                     <el-option
                        v-for="dict in sys_normal_disable"
                        :key="dict.value"
                        :label="dict.label"
                        :value="dict.value"
                     />
                  </el-select>
               </el-form-item>
               <el-form-item :label="t('user.createTime')" style="width: 308px;">
                  <el-date-picker
                     v-model="dateRange"
                     value-format="YYYY-MM-DD"
                     type="daterange"
                     range-separator="-"
                     :start-placeholder="t('user.startDate')"
                     :end-placeholder="t('user.endDate')"
                  ></el-date-picker>
               </el-form-item>
               <el-form-item>
                  <el-button type="primary" icon="Search" @click="handleQuery">{{ t('user.search') }}</el-button>
                  <el-button icon="Refresh" @click="resetQuery">{{ t('user.reset') }}</el-button>
               </el-form-item>
            </el-form>

            <el-row :gutter="10" class="mb8">
               <el-col :span="1.5">
                  <el-button
                     type="primary"
                     plain
                     icon="Plus"
                     @click="handleAdd"
                     v-hasPermi="['system:user:add']"
                  >{{ t('user.add') }}</el-button>
               </el-col>
               <el-col :span="1.5">
                  <el-button
                     type="success"
                     plain
                     icon="Edit"
                     :disabled="single"
                     @click="handleUpdate"
                     v-hasPermi="['system:user:edit']"
                  >{{ t('user.modify') }}</el-button>
               </el-col>
               <el-col :span="1.5">
                  <el-button
                     type="danger"
                     plain
                     icon="Delete"
                     :disabled="multiple"
                     @click="handleDelete"
                     v-hasPermi="['system:user:remove']"
                  >{{ t('user.delete') }}</el-button>
               </el-col>
               <el-col :span="1.5">
                  <el-button
                     type="info"
                     plain
                     icon="Upload"
                     @click="handleImport"
                     v-hasPermi="['system:user:import']"
                  >{{ t('user.import') }}</el-button>
               </el-col>
               <el-col :span="1.5">
                  <el-button
                     type="warning"
                     plain
                     icon="Download"
                     @click="handleExport"
                     v-hasPermi="['system:user:export']"
                  >{{ t('user.export') }}</el-button>
               </el-col>
               <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
            </el-row>

            <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
               <el-table-column type="selection" width="50" align="center" />
               <el-table-column :label="t('user.userNumber')" align="center" key="userId" prop="userId" v-if="columns[0].visible" />
               <el-table-column :label="t('user.userName')" align="center" key="userName" prop="userName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
               <el-table-column :label="t('user.userNickname')" align="center" key="nickName" prop="nickName" v-if="columns[2].visible" :show-overflow-tooltip="true" />
               <el-table-column :label="t('user.department')" align="center" key="deptName" prop="dept.deptName" v-if="columns[3].visible" :show-overflow-tooltip="true" />
               <el-table-column :label="t('user.phoneNumber')" align="center" key="phonenumber" prop="phonenumber" v-if="columns[4].visible" width="120" />
               <el-table-column :label="t('user.status')" align="center" key="status" v-if="columns[5].visible">
                  <template #default="scope">
                     <el-switch
                        v-model="scope.row.status"
                        active-value="0"
                        inactive-value="1"
                        @change="handleStatusChange(scope.row)"
                     ></el-switch>
                  </template>
               </el-table-column>
               <el-table-column :label="t('user.createTime')" align="center" prop="createTime" v-if="columns[6].visible" width="160">
                  <template #default="scope">
                     <span>{{ parseTime(scope.row.createTime) }}</span>
                  </template>
               </el-table-column>
               <el-table-column :label="t('user.operate')" align="center" width="150" class-name="small-padding fixed-width">
                  <template #default="scope">
                     <el-tooltip :content="t('user.modify')" placement="top" v-if="scope.row.userId !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:user:edit']"></el-button>
                     </el-tooltip>
                     <el-tooltip :content="t('user.delete')" placement="top" v-if="scope.row.userId !== 1">
                        <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['system:user:remove']"></el-button>
                     </el-tooltip>
                     <el-tooltip :content="t('user.resetPassword')" placement="top" v-if="scope.row.userId !== 1">
                         <el-button link type="primary" icon="Key" @click="handleResetPwd(scope.row)" v-hasPermi="['system:user:resetPwd']"></el-button>
                     </el-tooltip>
                     <el-tooltip :content="t('user.assignRoles')" placement="top" v-if="scope.row.userId !== 1">
                        <el-button link type="primary" icon="CircleCheck" @click="handleAuthRole(scope.row)" v-hasPermi="['system:user:edit']"></el-button>
                     </el-tooltip>
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
         </el-col>
      </el-row>

      <!-- Add or modify user configuration dialog box -->
      <el-dialog :title="title" v-model="open" width="600px" append-to-body>
         <el-form :model="form" :rules="rules" ref="userRef" label-width="80px">
            <el-row>
               <el-col :span="12">
                  <el-form-item :label="t('user.userNickname')"prop="nickName">
                     <el-input v-model="form.nickName" placeholder="Please enter user nickname" maxlength="30" />
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item :placeholder="t('user.enterUserNickname')" prop="deptId">
                     <el-tree-select
                        v-model="form.deptId"
                        :data="deptOptions"
                        :props="{ value: 'id', label: 'label', children: 'children' }"
                        value-key="id"
                        :placeholder="t('user.selectDepartment')"
                        check-strictly
                     />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="12">
                  <el-form-item :label="t('user.phoneNumber')" prop="phonenumber">
                     <el-input v-model="form.phonenumber" :placeholder="t('user.enterPhoneNumber')" maxlength="11" />
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item :label="t('user.email')" prop="email">
                     <el-input v-model="form.email" :placeholder="t('user.enterEmail')" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="12">
                  <el-form-item v-if="form.userId == undefined" :label="t('user.userName')" prop="userName">
                     <el-input v-model="form.userName" :placeholder="t('user.enterUserName')" maxlength="30" />
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item v-if="form.userId === undefined" :label="t('user.password')" prop="password">
                     <el-input v-model="form.password" :placeholder="t('user.enterPassword')" type="password" maxlength="20" show-password />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="12">
                  <el-form-item :label="t('user.gender')">
                     <el-select v-model="form.sex" :placeholder="t('user.select')">
                        <el-option v-for="dict in sys_user_sex" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
                     </el-select>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item :label="t('user.status')">
                     <el-radio-group v-model="form.status">
                        <el-radio v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.value">{{ dict.label }}</el-radio>
                     </el-radio-group>
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="12">
                  <el-form-item :label="t('user.jobTitle')">
                     <el-select v-model="form.postIds" multiple :placeholder="t('user.select')">
                        <el-option v-for="item in postOptions" :key="item.postId" :label="item.postName" :value="item.postId" :disabled="item.status === 1"></el-option>
                     </el-select>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item :label="t('user.role')">
                     <el-select v-model="form.roleIds" multiple :placeholder="t('user.select')">
                        <el-option v-for="item in roleOptions" :key="item.roleId" :label="item.roleName" :value="item.roleId" :disabled="item.status === 1"></el-option>
                     </el-select>
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item :label="t('user.remark')">
                     <el-input v-model="form.remark" type="textarea" :placeholder="t('user.enterRemark')"></el-input>
                  </el-form-item>
               </el-col>
            </el-row>
         </el-form>
         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitForm">{{ t('user.confirm') }}</el-button>
               <el-button @click="cancel">{{ t('user.cancel') }}</el-button>
            </div>
         </template>
      </el-dialog>

      <!-- User Import Dialog -->
      <el-dialog :title="upload.title" v-model="upload.open" width="400px" append-to-body>
         <el-upload
             ref="uploadRef"
             :limit="1"
             accept=".xlsx, .xls"
             :headers="upload.headers"
             :action="upload.url + '?updateSupport=' + upload.updateSupport"
             :disabled="upload.isUploading"
             :on-progress="handleFileUploadProgress"
             :on-success="handleFileSuccess"
             :auto-upload="false"
             drag
         >
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">{{ t('user.dragFilesHereOrClickToUpload') }}</div>
            <template #tip>
               <div class="el-upload__tip text-center">
                  <div class="el-upload__tip">
                     <el-checkbox v-model="upload.updateSupport">{{ t('user.updateExistingUserData') }}</el-checkbox>
                  </div>
                  <span>{{ t('user.onlyXLSXFiles') }}</span>
                  <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">{{ t('user.downloadTemplate') }}</el-link>
               </div>
            </template>
         </el-upload>
         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitFileForm">{{ t('user.confirm') }}</el-button>
               <el-button @click="upload.open = false">{{ t('user.cancel') }}</el-button>
            </div>
         </template>
      </el-dialog>
   </div>

</template>

<style scoped>
/* Scoped styles will apply only to this component. Remove 'scoped' if these styles need to be global */
.nowrap .cell {
   white-space: nowrap;
}
</style>

<script setup name="User">
import { getToken } from "@/utils/auth";
import { changeUserStatus, listUser, resetUserPwd, delUser, getUser, updateUser, addUser, deptTreeSelect } from "@/api/system/user";
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
const router = useRouter();
const { proxy } = getCurrentInstance();
const { sys_normal_disable, sys_user_sex } = proxy.useDict("sys_normal_disable", "sys_user_sex");

const userList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const dateRange = ref([]);
const deptName = ref("");
const deptOptions = ref(undefined);
const initPassword = ref(undefined);
const postOptions = ref([]);
const roleOptions = ref([]);
/*** 用户导入参数 */
const upload = reactive({
  // 是否显示弹出层（用户导入）
  open: false,
  // 弹出层标题（用户导入）
  title: "",
  // 是否禁用上传
  isUploading: false,
  // 是否更新已经存在的User data
  updateSupport: 0,
  // 设置上传的请求头部
  headers: { Authorization: "Bearer " + getToken() },
  // 上传的地址
  url: import.meta.env.VITE_APP_BASE_API + "/system/user/importData"
});
// 列显隐信息
const columns = ref([
   { key: 0, label: t('user.userNumber'), visible: true },
   { key: 1, label: t('user.userName'), visible: true },
   { key: 2, label: t('user.userNickname'), visible: true },
   { key: 3, label: t('user.department'), visible: true },
   { key: 4, label: t('user.phoneNumber'), visible: true },
   { key: 5, label: t('user.status'), visible: true },
   { key: 6, label: t('user.createTime'), visible: true }
]);


const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userName: undefined,
    phonenumber: undefined,
    status: undefined,
    deptId: undefined
  },
  rules: {
    userName: [{ required: true, message: t('user.userNameRequired'), trigger: "blur" }, { min: 2, max: 20, message: "Username length must be between 2 and 20 between", trigger: "blur" }],
    nickName: [{ required: true, message: t('user.nickNameRequired'), trigger: "blur" }],
    password: [{ required: true, message: t('user.passwordRequired'), trigger: "blur" }, { min: 5, max: 20, message: "User password length must be between 5 and 20 between", trigger: "blur" }, { pattern: /^[^<>"'|\\]+$/, message: "Cannot contain illegal characters：< > \" ' \\\ |", trigger: "blur" }],
    email: [{ type: "email", message: t('user.validEmail'), trigger: ["blur", "change"] }],
    phonenumber: [{ pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: t('user.validPhoneNumber'), trigger: "blur" }]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 通过条件过滤节点  */
const filterNode = (value, data) => {
  if (!value) return true;
  return data.label.indexOf(value) !== -1;
};
/** 根据名称筛选部门树 */
watch(deptName, val => {
  proxy.$refs["deptTreeRef"].filter(val);
});
/** 查询部门下拉树结构 */
function getDeptTree() {
  deptTreeSelect().then(response => {
    deptOptions.value = response.data;
  });
};
/** 查询用户列表 */
function getList() {
  loading.value = true;
  listUser(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
     console.log(res)
    loading.value = false;
    userList.value = res.rows;
    total.value = res.total;
  });
};
/** 节点单击事件 */
function handleNodeClick(data) {
  queryParams.value.deptId = data.id;
  handleQuery();
};
/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
};
/** 重置按钮操作 */
function resetQuery() {
  dateRange.value = [];
  proxy.resetForm("queryRef");
  queryParams.value.deptId = undefined;
  proxy.$refs.deptTreeRef.setCurrentKey(null);
  handleQuery();
};
/** 删除按钮操作 */
function handleDelete(row) {
  const userIds = row.userId || ids.value;
  proxy.$modal.confirm(t('user.confirmDelete', { userIds: userIds })).then(function () {
    return delUser(userIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess(t('user.deleteUserSuccess'));
  }).catch(() => {});
};
/** 导出按钮操作 */
function handleExport() {
  proxy.download("system/user/export", {
    ...queryParams.value,
  },`user_${new Date().getTime()}.xlsx`);
};
/** 用户状态修改  */
function handleStatusChange(row) {
  let text = row.status === "0" ? t('user.activate') : t('user.deactivate');
  proxy.$modal.confirm(t('user.confirmStatusChange', { status: text, userName: row.userName })).then(function () {
    return changeUserStatus(row.userId, row.status);
  }).then(() => {
    proxy.$modal.msgSuccess(t('user.statusChangeSuccess', { status: text }));
  }).catch(function () {
    row.status = row.status === "0" ? "1" : "0";
  });
};
/** 更多操作 */
function handleCommand(command, row) {
  switch (command) {
    case "handleResetPwd":
      handleResetPwd(row);
      break;
    case "handleAuthRole":
      handleAuthRole(row);
      break;
    default:
      break;
  }
};
/** 跳转角色分配 */
function handleAuthRole(row) {
  const userId = row.userId;
  router.push("/system/user-auth/role/" + userId);
};
/** 重置密码按钮操作 */
function handleResetPwd(row) {
   proxy.$prompt(t('user.inputNewPwd', { userName: row.userName }), t('user.prompt'), {
      confirmButtonText: t('user.confirm'),
      cancelButtonText: t('user.cancel'),
      closeOnClickModal: false,
      inputPattern: /^.{5,20}$/,
      inputErrorMessage: t('user.passwordLength'),
      inputValidator: (value) => {
         if (/<|>|"|'|\||\\/.test(value)) {
            return t('user.illegalCharacters');
         }
      },
   }).then(({ value }) => {
      resetUserPwd(row.userId, value).then(response => {
         proxy.$modal.msgSuccess(t('user.updateSuccess', { newPassword: value }));
      });
   }).catch(() => {});
};

/** 选择条数  */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.userId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
};
/** 导入按钮操作 */
function handleImport() {
   upload.title = t('user.importUser');
   upload.open = true;
};

/** 下载模板操作 */
function importTemplate() {
  proxy.download("system/user/importTemplate", {
  }, `user_template_${new Date().getTime()}.xlsx`);
};
/**文件上传中处理 */
const handleFileUploadProgress = (event, file, fileList) => {
  upload.isUploading = true;
};
/** 文件上传成功处理 */
const handleFileSuccess = (response, file, fileList) => {
  upload.open = false;
  upload.isUploading = false;
  proxy.$refs["uploadRef"].handleRemove(file);
  proxy.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "Import results", { dangerouslyUseHTMLString: true });
  getList();
};
/** 提交上传文件 */
function submitFileForm() {
  proxy.$refs["uploadRef"].submit();
};
/** 重置操作表单 */
function reset() {
  form.value = {
    userId: undefined,
    deptId: undefined,
    userName: undefined,
    nickName: undefined,
    password: undefined,
    phonenumber: undefined,
    email: undefined,
    sex: undefined,
    status: "0",
    remark: undefined,
    postIds: [],
    roleIds: []
  };
  proxy.resetForm("userRef");
};
/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
};
/** 新增按钮操作 */
function handleAdd() {
  reset();
  getUser().then(response => {
    postOptions.value = response.posts;
    roleOptions.value = response.roles;
    open.value = true;
    title.value = t('user.addUser');
    form.value.password = initPassword.value;
  });
};
/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const userId = row.userId || ids.value;
  getUser(userId).then(response => {
    form.value = response.data;
    postOptions.value = response.posts;
    roleOptions.value = response.roles;
    form.value.postIds = response.postIds;
    form.value.roleIds = response.roleIds;
    open.value = true;
    title.value = t('user.addUser');
    form.password = "";
  });
};
/** 提交按钮 */
function submitForm() {
  proxy.$refs["userRef"].validate(valid => {
    if (valid) {
      if (form.value.userId != undefined) {
        updateUser(form.value).then(response => {
          proxy.$modal.msgSuccess(t('user.updateUserSuccess'));
          open.value = false;
          getList();
        });
      } else {
        addUser(form.value).then(response => {
          proxy.$modal.msgSuccess(t('user.addUserSuccess'));
          open.value = false;
          getList();
        });
      }
    }
  });
};

getDeptTree();
getList();
</script>
