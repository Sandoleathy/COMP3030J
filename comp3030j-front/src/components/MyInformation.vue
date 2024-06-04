<template>
  <el-card class="box-card" shadow="hover">
    <el-form label-width="100px" v-loading="isLoadingInfo">
      <el-row>
        <el-col :span="12">
          <el-form-item>
            <h2>Profile</h2>
            <el-divider></el-divider>
          </el-form-item>

          <el-row>
            <el-col :span="24">
              <el-form-item :label="t('myInformation.account')" label-width="150px">
                <template v-if="!isEditing">
                  <p>{{ userName }}</p>
                </template>
                <el-input v-else v-model="userName" :placeholder="t('myInformation.account')"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item :label="t('myInformation.name')">
                <template v-if="!isEditing">
                  <p>{{ name }}</p>
                </template>
                <el-input v-else v-model="name" :placeholder="t('myInformation.name')"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item :label="t('myInformation.phoneNumber')" label-width="150px">
                <template v-if="!isEditing">
                  <p>{{ phoneNumber }}</p>
                </template>
                <el-input v-else v-model="phoneNumber" :placeholder="t('myInformation.phoneNumber')"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item :label="t('myInformation.email')">
                <template v-if="!isEditing">
                  <p>{{ email }}</p>
                </template>
                <el-input v-else v-model="email" :placeholder="t('myInformation.email')"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item :label="t('myInformation.sex')">
                <template v-if="!isEditing">
                  <p>{{ sexText }}</p>
                </template>
                <el-select v-else v-model="sex" :placeholder="t('myInformation.chooseSex')">
                  <el-option :label="t('myInformation.male')" value="0"></el-option>
                  <el-option :label="t('myInformation.female')" value="1"></el-option>
                  <el-option :label="t('myInformation.unknown')" value="unknown"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-divider></el-divider>

          <el-row>
            <el-col :span="24">
              <el-form-item>
                <el-button v-if="!isEditing" type="primary" @click="isEditing = true">
                  <el-icon><Edit /></el-icon> Edit
                </el-button>
                <el-button v-if="!isEditing" type="danger" @click="isLoggingOut = true">
                  {{ t('myInformation.logout') }}
                </el-button>
                <el-button v-if="isEditing" type="success" @click="saveChanges">
                  <el-icon><Check /></el-icon> Save
                </el-button>
                <el-button v-if="isEditing" type="default" @click="cancelChanges">
                  <el-icon><Close /></el-icon> Cancel
                </el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="12">
          <div class="avatar_div">
            <el-avatar :size="280" :src="avatarPath"></el-avatar>
            <el-icon v-if="isEditing" class="avatar_icon" @click="isUploadDialogVisible = true">
              <Avatar />
            </el-icon>
          </div>
        </el-col>
      </el-row>


      <!--弹窗-->
      <el-dialog title="Upload File" v-model="isUploadDialogVisible">
        <el-upload
            action="/api/system/user/profile/avatar"
            list-type="text"
            :limit="1"
            :on-exceed="handleExceed"
            :auto-upload="false"
            drag
            accept="image/*"
            ref="upload"
            :on-success="handleUploadSuccess"
            :on-error="handleUploadError"
            :before-upload="beforeUpload"
        >
          <el-icon class="el-icon--upload"><upload-filled /></el-icon>
          <div class="el-upload__text">
            Drop file here or <em>click to upload</em>
          </div>
          <template #tip>
            <div class="el-upload__tip">
              jpg/png files with a size less than 500kb
            </div>
          </template>
        </el-upload>
        <el-button type="success" @click="submitUpload">Upload</el-button>
      </el-dialog>
    </el-form>
  </el-card>
  <el-dialog v-model="isLoggingOut" :title="t('myInformation.logout')">
    <span>{{t("myInformation.logoutTips")}}</span>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="isLoggingOut = false">Cancel</el-button>
        <el-button type="danger" @click="logout">
          Confirm
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import axios from "@/axios/index.js";
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'
import { genFileId } from 'element-plus'
import { UploadFilled } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';

const isLoggingOut = ref(false)
const router = useRouter();

let phoneNumber = ref('');
let email = ref('');
let name = ref('');
let userName = ref('');
let sex = ref('');
let isEditing = ref(false);
let avatarPath = ref("");

let isLoadingInfo = ref(false); //用于加载动画
let isUploadDialogVisible = ref(false);

const sexText = computed(() => {
    return sex.value === "0" ? "male" : sex.value === "1" ? "female" : "unknown";
});

onMounted(() => {
    getMyInfo();
});

const upload = ref<UploadInstance>()
//处理图片上传的方法
const submitUpload = () => {
  console.log(upload.value)
  upload.value?.submit()
}
//删除多余文件保证只有一个文件
const handleExceed: UploadProps['onExceed'] = (files) => {
  upload.value!.clearFiles()
  const file = files[0] as UploadRawFile
  file.uid = genFileId()
  upload.value!.handleStart(file)
}
const handleUploadSuccess = (response:any, file:any, fileList:any) => {
  console.log('Upload success:', response, file, fileList);
}
const handleUploadError = (error:any, file:any, fileList:any) => {
  console.error('Upload failed:', error, file, fileList);
}
const fileList:any = []
const beforeUpload = (file:any) => {
  const formData = new FormData();
  const token = sessionStorage.getItem('token')
  formData.append('avatarfile', file);


  // 自定义上传请求
  const xhr = new XMLHttpRequest();
  xhr.open('POST', '/api/system/user/profile/avatar', true);

  // 设置自定义HTTP头部参数
  xhr.setRequestHeader('Authorization', 'Bearer ' + token);

  // 监听上传过程
  xhr.onload = () => {
    if (xhr.status === 200) {
      handleUploadSuccess(JSON.parse(xhr.responseText), file , fileList);
    } else {
      handleUploadError(xhr.statusText, file , fileList);
    }
  };

  xhr.onerror = () => {
    handleUploadError(xhr.statusText, file, fileList);
  };

  // 发送请求
  xhr.send(formData);

  // 返回 false 阻止默认的上传行为
  return false;
}

const logout = () => {
  sessionStorage.removeItem("token")
  sessionStorage.removeItem("username")
  router.push({ name: 'login' })
  isLoggingOut.value = false
}

const getMyInfo = async () => {
    isLoadingInfo.value = true;
    try {
        const token = sessionStorage.getItem("token");
        //console.log("Token is:", token);
        const response = await axios.get("/api/system/user/profile", {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });

        const data = response.data;
        console.log(data)
        phoneNumber.value = data.data.phonenumber;
        name.value = data.data.nickName;
        email.value = data.data.email;
        userName.value = data.data.userName;
        sex.value = data.data.sex.toString();
        avatarPath.value = data.data.avatar;
        isLoadingInfo.value = false;
    } catch (error) {
        console.error('Error fetching data:', error);
        isLoadingInfo.value = false;
    }
};

const saveChanges = async () => {
    console.log('Saving changes...');

    try {
        // 假设 token 已正确获取并存储在 sessionStorage
        const token = sessionStorage.getItem('token');

        // 构建要更新的数据对象
        const updatedData = {
            phonenumber: phoneNumber.value,
            nickName: name.value,
            email: email.value,
            userName: userName.value,
            sex: sex.value
        };

        // 发送 PUT 请求到后端
        const response = await axios.put("/api/system/user/profile", updatedData, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });


        const data = response.data;
        // 处理响应
        if (data) {
            console.log('Update successful:', data.email);
            await getMyInfo();
        } else {
            console.error('No data received from the server.');
        }
    } catch (error) {
        console.error('Error updating profile:', error);
    }

    // 无论更新成功与否，都结束编辑状态
    isEditing.value = false;
};

const cancelChanges = () => {
    console.log('Cancelling changes...');
    // Optionally reload original data
    getMyInfo();
    isEditing.value = false;
};

</script>

<style scoped>
.el-form {
    font-family: 'Arial', sans-serif;
    color: #333;
    font-weight: bold; /* 加粗所有表单内的文字 */
    padding: 20px;
    border-radius: 8px;
}

.el-form-item {
    margin-bottom: 20px;
    margin-left: 50px;
}

.el-form-item label {
    color: white;
    font-size: 40px; /* 标签字体大小 */
    font-weight: bold; /* 加粗标签文字 */
}

.el-input,
.el-select {
    width: 100%;
    border: 1px solid #dcdfe6;
    border-radius: 6px;
    padding: 10px 15px;
    font-weight: bold; /* 加粗输入框和下拉选择框内的文字 */
}

.el-button {
    margin-right: 8px;
    border: none;
    border-radius: 6px;
    transition: background-color 0.3s, box-shadow 0.3s;
    font-weight: bold; /* 加粗按钮上的文字 */
}

.el-button:hover {
    box-shadow: 0 2px 4px rgba(0,0,0,0.2);
}

.el-button-primary {
    background-color: #409eff;
    color: white;
}

.el-button-success {
    background-color: #67c23a;
    color: white;
}

.el-button-default {
    background-color: #dcdfe6;
}
.box-card {
  margin: 20px;
  padding: 20px;
}
.avatar_div{
  text-align: center;
  margin: auto;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  position: relative;
}
.avatar_icon {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 48px;
  color: rgba(255, 255, 255, 0.9);
  cursor: pointer;
  background: rgba(0, 0, 0, 0.5);
  border-radius: 50%;
  padding: 10px;
  width: 200px;
  height: 200px;
}
</style>

