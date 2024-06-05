<template>
   <el-form ref="pwdRef" :model="user" :rules="rules" label-width="80px">
      <el-form-item label="Old Password" prop="oldPassword">
         <el-input v-model="user.oldPassword" placeholder="Please enter old password" type="password" show-password />
      </el-form-item>
      <el-form-item label="New Password" prop="newPassword">
         <el-input v-model="user.newPassword" placeholder="Please enter a new password" type="password" show-password />
      </el-form-item>
      <el-form-item label="Confirm Password" prop="confirmPassword">
         <el-input v-model="user.confirmPassword" placeholder="Please confirm new password" type="password" show-password/>
      </el-form-item>
      <el-form-item>
      <el-button type="primary" @click="submit">save</el-button>
      <el-button type="danger" @click="close">closure</el-button>
      </el-form-item>
   </el-form>
</template>

<script setup>
import { updateUserPwd } from "@/api/system/user";

const { proxy } = getCurrentInstance();

const user = reactive({
  oldPassword: undefined,
  newPassword: undefined,
  confirmPassword: undefined
});

const equalToPassword = (rule, value, callback) => {
  if (user.newPassword !== value) {
    callback(new Error("The passwords entered twice are inconsistent"));
  } else {
    callback();
  }
};
const rules = ref({
  oldPassword: [{ required: true, message: "The old password cannot be empty", trigger: "blur" }],
  newPassword: [{ required: true, message: "New password cannot be empty", trigger: "blur" }, { min: 6, max: 20, message: "The length is 6 arrive 20 characters", trigger: "blur" }, { pattern: /^[^<>"'|\\]+$/, message: "Cannot contain illegal characters：< > \" ' \\\ |", trigger: "blur" }],
  confirmPassword: [{ required: true, message: "confirm password can not be blank", trigger: "blur" }, { required: true, validator: equalToPassword, trigger: "blur" }]
});

/** 提交按钮 */
function submit() {
  proxy.$refs.pwdRef.validate(valid => {
    if (valid) {
      updateUserPwd(user.oldPassword, user.newPassword).then(response => {
        proxy.$modal.msgSuccess("Successfully modified");
      });
    }
  });
};
/** closure按钮 */
function close() {
  proxy.$tab.closePage();
};
</script>
