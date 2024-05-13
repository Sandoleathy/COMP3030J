<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import axios from "axios";

let bedType=ref('')

onMounted(() => {
    getRoomInfo();
});

const getRoomInfo = () => {
    axios.get("/api/homestay/room/list", {

    }).then(response => {
        const data = response.data;
        console.log(data)
        bedType.value=data.bedType

    }).catch(error => {
        console.error('Error fetching data:', error);

    });
};


</script>

<template>
    <div class="page-container">
        <el-row class="row-container">
            <el-col :span="5" class="column">
                <img src="/images/pic4.png" alt="Room Information" style="width: 35vh; height: 25vh;">
                <div>
                    <el-link>View Room Information</el-link>
                </div>
            </el-col>
            <el-col :span="4" class="column">
                <div>
                    <h2>{{bedType}}</h2>
                    <h4>One king-size bed</h4>
                    <h4>Area: 34m²</h4>
                    <h4>With Window</h4>
                    <h4>Non-smoking</h4>
                </div>
            </el-col>
            <el-col :span="2" class="column">
                <div class="icons-container">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" style="cursor: pointer; width: 20px; height: 20px; fill: currentColor;">
                        <path fill="currentColor" d="M288 320a224 224 0 1 0 448 0 224 224 0 1 0-448 0m544 608H160a32 32 0 0 1-32-32v-96a160 160 0 0 1 160-160h448a160 160 0 0 1 160 160v96a32 32 0 0 1-32 32z"></path>
                    </svg>
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" style="cursor: pointer; width: 20px; height: 20px; fill: currentColor;">
                        <path fill="currentColor" d="M288 320a224 224 0 1 0 448 0 224 224 0 1 0-448 0m544 608H160a32 32 0 0 1-32-32v-96a160 160 0 0 1 160-160h448a160 160 0 0 1 160 160v96a32 32 0 0 1-32 32z"></path>
                    </svg>
                </div>
            </el-col>

            <el-col :span="3" class="column">
                <h3>Includes 2 Breakfasts</h3>
            </el-col>
            <el-col :span="4" class="column">
                <h3>Free Cancellation within 30 Minutes</h3>
            </el-col>
            <el-col :span="6" class="column">
                <div>
                    <h3>¥366</h3>
                    <el-button>Book Now</el-button>
                </div>
            </el-col>
        </el-row>
    </div>
</template>



<style scoped>
.page-container {
    width: 95%;
    background: white;
    margin: auto auto 20px;
}

.row-container {
    width: 100%;
    margin: 0 auto;
    align-items: center;
}

.column {
    display: flex;
    flex-direction: column;
    align-items: center; /* 垂直居中 */
    justify-content: center; /* 水平居中 */
    height: 100%; /* 确保足够高度以展示垂直居中 */
    text-align: center; /* 文本对齐 */
}

.el-link{
    color: blue;
}
.el-link .el-icon--right.el-icon {
    vertical-align: text-bottom;

}
.icons-container {
    display: flex; /* 使用flex布局 */
    align-items: center; /* 垂直居中 */
    justify-content: space-evenly; /* 水平平分空间 */
}

</style>
