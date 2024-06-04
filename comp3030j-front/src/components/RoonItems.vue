<script lang="ts" setup>
import {computed, defineProps, onMounted, ref, watchEffect, reactive} from 'vue';
import axios from "axios";
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
const { t } = useI18n();

const router = useRouter();
const props = defineProps({
    roomData: Object,
    dateRange: Array,
    buildingType: String
});

const roomType = ref('');
const price = ref('');
const bedType = ref('');
const smoking=ref('');
const breakfastIncludes = ref(''); // 存储早餐信息
const gridData = ref([]);

// 使用 computed 属性确保任何 props.roomData 的更新都能被捕捉和处理
watchEffect(() => {
    // console.log('Received room data in roomItems:', props.roomData);
    roomType.value = props.roomData ? props.roomData.roomType : 'Undefined';
    price.value = props.roomData ? props.roomData.roomPrice : '0';
    bedType.value = props.roomData && props.roomData.bedType ? props.roomData.bedType.toString() : 'Unknown';
    if (props.roomData && props.roomData.buildingType) {
        fetchBuildingTypeData(props.roomData.buildingType);
    }
    console.log('Received dateRange:', props.dateRange);
    console.log('Received buildingType:', props.buildingType);
    if (props.dateRange && props.dateRange.length >= 2) {
        gridData.value = [
            {
                Start_Date: formatDate(props.dateRange[0]),
                End_Date: formatDate(props.dateRange[1]),
                Building_Type: props.buildingType || 'N/A'
            },
        ];
    } else {
        gridData.value = [
            {
                Start_Date: 'N/A',
                End_Date: 'N/A',
                Building_Type: 'N/A'
            }
        ];
    }
});
function fetchBuildingTypeData(buildingTypeId) {
    axios.get(`/api/homestay/buildingType/${buildingTypeId}`, {
        headers: {
            'Authorization': 'Bearer ' + sessionStorage.getItem("token")
        }
    })
        .then(response => {
            // 假设API返回的数据中有一个字段是breakfast，表明是否包含早餐
            breakfastIncludes.value = response.data.data.breakfast;
            const smokingStatus = response.data.data.allowSmoking;
            smoking.value = smokingStatus === 1 ? "no smoking" : "can smoking";

            console.log('Building Type Data:', response.data.data.breakfast);
        })
        .catch(error => {
            console.error('Failed to fetch building type data:', error);
        });
}
function formatDate(dateString) {
    const date = new Date(dateString);
    const day = date.getDate();
    const month = date.toLocaleString('default', { month: 'short' }); // 获取月份的缩写
    const year = date.getFullYear();
    return `${day} ${month} ${year}`; // 格式: 12 Jun 2024
}



const bedtype = computed(() => {
    return bedType.value === "0" ? "twin beds" : bedType.value === "1" ? "One king-size bed" : "Unknown";
});

function goToRoomDetails() {
    router.push('/roomdetails');
}

const dialogFormVisible = ref(false)

const form = reactive({
    userName: '',
    phoneNum: '',
})

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
        form.phoneNum= data.data.phonenumber;
        form.userName = data.data.userName;
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};

getMyInfo();

const confirmReservation = async () => {
    try {
        const token = sessionStorage.getItem("token");
        const response = await axios.post('/homestay/reservation/add', form, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });
        console.log('Reservation added successfully:', response.data);
        dialogFormVisible.value = false;  // 关闭对话框
    } catch (error) {
        console.error('Failed to add reservation:', error);
        // 根据需要处理错误，比如显示错误消息
    }
};

</script>


<template>
    <div class="page-container">
        <el-row class="row-container">
            <el-col :span="5" class="column">
                <img src="/images/pic4.png" alt="Room Information" style="width: 35vh; height: 25vh;">
                <div>
                    <el-link @click="goToRoomDetails"> {{ t('roonItems.view') }}</el-link>
                </div>
            </el-col>
            <el-col :span="4" class="column">
                <div>
                    <h2>{{roomType}}</h2>
                    <h4>{{bedtype}}</h4>
                    <h4>Area: 34m²</h4>
                    <h4>With Window</h4>
                    <h4>{{smoking}}</h4>
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
                <h3>Includes {{ breakfastIncludes }} {{ t('roonItems.breakfast') }}</h3>
            </el-col>
            <el-col :span="4" class="column">
                <h3>{{ t('roonItems.free') }}</h3>
            </el-col>
            <el-col :span="6" class="column">
                <div>
                    <h3>¥{{price}}</h3>
                    <el-button plain @click="dialogFormVisible = true">Book Now</el-button>
                    <el-dialog v-model="dialogFormVisible" title="Reservation Confirm" width="500">
                        <el-form :model="form">
                            <el-table :data="gridData">
                                <el-table-column property="Start_Date" label="Start Date" width="150" />
                                <el-table-column property="End_Date" label="End Date" width="200" />
                                <el-table-column label="Room Type">
                                    <template #default="scope">
                                        {{ roomType }}
                                    </template>
                                </el-table-column>
                                <el-table-column property="Building_Type" label="Building Type" />
                            </el-table>
                            <el-form-item label="Promotion name" :label-width="200">
                                <el-input v-model="form.userName" autocomplete="off" />
                            </el-form-item>
                            <el-form-item label="Zones" :label-width="200">
                                <el-input-number v-model="form.phoneNum" autocomplete="off">
                                </el-input-number>
                            </el-form-item>
                        </el-form>
                        <template #footer>
                            <div class="dialog-footer">
                                <el-button @click="dialogFormVisible = false">Cancel</el-button>
                                <el-button type="primary" @click="confirmReservation">
                                    Confirm
                                </el-button>
                            </div>
                        </template>
                    </el-dialog>
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
