<template>
    <el-form ref="form" label-width="100px">
        <el-form-item label="个人信息登记:">
        </el-form-item>

        <el-form-item label="AccountNumber:">
            <template v-if="!isEditing">
                <p>{{ userName }}</p>
            </template>
            <el-input v-else v-model="userName" placeholder="Account Number"></el-input>
        </el-form-item>

        <el-form-item label="Name:">
            <template v-if="!isEditing">
                <p>{{ name }}</p>
            </template>
            <el-input v-else v-model="name" placeholder="Name"></el-input>
        </el-form-item>

        <el-form-item label="PhoneNumber:">
            <template v-if="!isEditing">
                <p>{{ phoneNumber }}</p>
            </template>
            <el-input v-else v-model="phoneNumber" placeholder="Phone Number"></el-input>
        </el-form-item>

        <el-form-item label="Email:">
            <template v-if="!isEditing">
                <p>{{ email }}</p>
            </template>
            <el-input v-else v-model="email" placeholder="Email"></el-input>
        </el-form-item>

        <el-form-item label="Sex:">
            <template v-if="!isEditing">
                <p>{{ sexText }}</p>
            </template>
            <el-select v-else v-model="sex" placeholder="Select sex">
                <el-option label="男" value="0"></el-option>
                <el-option label="女" value="1"></el-option>
                <el-option label="未知" value="unknown"></el-option>
            </el-select>
        </el-form-item>

        <el-form-item>
            <el-button v-if="!isEditing" type="primary" @click="isEditing = true">Edit</el-button>
            <el-button v-if="isEditing" type="success" @click="saveChanges">Save</el-button>
            <el-button v-if="isEditing" type="default" @click="cancelChanges">Cancel</el-button>
        </el-form-item>
    </el-form>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import axios from "axios";

let phoneNumber = ref('');
let email = ref('');
let name = ref('');
let userName = ref('');
let sex = ref('');
let isEditing = ref(false);

const sexText = computed(() => {
    return sex.value === "0" ? "男" : sex.value === "1" ? "女" : "未知";
});

onMounted(() => {
    getMyInfo();
});

const getMyInfo = async () => {
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
    } catch (error) {
        console.error('Error fetching data:', error);
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
</style>

