<script setup>
import { computed, defineProps, ref, watchEffect, reactive, onMounted } from 'vue';
import axios from 'axios';
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
const smoking = ref('');
const breakfastIncludes = ref('');
const roomId=ref('');
const gridData = ref([]);

const dialogFormVisible = ref(false);
const form = reactive({
    userName: '',
    phoneNum: '',

    hsReservation: {
        contactInformation: '',
        checkinTime: '',
        checkoutTime: '',
        numberOfGuests: 0,
        numberOfRooms: 0,
        reservationTime: '',
        requests: '',
        totalPrice: 0,
        pay: 0,
        reservationStatus: ''
    },
    roomIds: [],
    userIds: [],

});

watchEffect(() => {
    console.log("Room Data:", props.roomData);
    roomType.value = props.roomData ? props.roomData.roomType : 'undefined';
    price.value = props.roomData ? props.roomData.roomPrice : '0';
    bedType.value = props.roomData && props.roomData.bedType ? props.roomData.bedType.toString() : 'unknown';
    if (props.roomData && props.roomData.buildingType) {
        fetchBuildingTypeData(props.roomData.buildingType);
    }
    if (props.dateRange && props.dateRange.length >= 2) {
        gridData.value = [
            {
                Start_Date: formatDateOnly(props.dateRange[0]),
                End_Date: formatDateOnly(props.dateRange[1]), // 退房时间
                Building_Type: props.buildingType || 'none'
            },
        ];
        form.hsReservation.checkinTime = formatDate(props.dateRange[0]);  // 使用 formatDate 来格式化日期
        form.hsReservation.checkoutTime = formatDate(props.dateRange[1]);
        form.hsReservation.reservationTime=props.dateRange[0];
    } else {
        gridData.value = [
            {
                Start_Date: 'none',
                End_Date: 'none',
                Building_Type: 'none'
            }
        ];
    }
    if (props.roomData) {
        form.roomIds = [props.roomData.id];
        form.hsReservation.numberOfRooms=props.roomData.roomNumber;
    } else {
        console.error("Invalid room ID:");
    }
    form.hsReservation.totalPrice = Number(price.value);
    form.hsReservation.reservationTime=getCurrentTime();
    form.hsReservation.reservationStatus="Successful booking";


});

function getCurrentTime() {
    return new Date().toISOString();
}

function fetchBuildingTypeData(buildingTypeId) {
    axios.get(`/api/homestay/buildingType/${buildingTypeId}`, {
        headers: {
            'Authorization': 'Bearer ' + sessionStorage.getItem("token")
        }
    }).then(response => {
        breakfastIncludes.value = response.data.data.breakfast;
        smoking.value = response.data.data.allowSmoking === 1 ? "No smoking" : "Smoking allowed";
    }).catch(error => {
        console.error('Failed to obtain building type data:', error);
    });
}

function formatDate(dateString) {
    const date = new Date(dateString);
    // const day = date.getDate();
    // const month = date.toLocaleString('default', { month: 'short' });

    const localIsoString = new Date(date.getFullYear(), date.getMonth(), date.getDate()+1).toISOString();
    // 截取日期部分
    // return localIsoString.slice(0, 10);
    return localIsoString;
}
function formatDateOnly(dateString) {
    const date = new Date(dateString);
    // const day = date.getDate();
    // const month = date.toLocaleString('default', { month: 'short' });

    const localIsoString = new Date(date.getFullYear(), date.getMonth(), date.getDate()+1).toISOString();
    // 截取日期部分
    return localIsoString.slice(0, 10);

}

const bedtype = computed(() => {
    return bedType.value === "0" ? "Twin bed" : bedType.value === "1" ? "big bed" : "unknown";
});

function goToRoomDetails() {
    router.push('/roomdetails');
}

const getMyInfo = async () => {
    try {
        const token = sessionStorage.getItem("token");
        const response = await axios.get("/api/system/user/profile", {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });
        if (response.data && response.data.data) {
            form.phoneNum = response.data.data.phonenumber;
            form.userName = response.data.data.userName;
            form.userIds = [response.data.data.userId];
            console.log("User Info Loaded:", form.userName, form.phoneNum, form.userIds);
        } else {
            console.error('No user data received');
        }
    } catch (error) {
        console.error('Failed to obtain user information:', error);
    }
};

const confirmReservation = async () => {
    console.log('Preparing to send reservation:', form.hsReservation, "Room IDs:", form.roomIds, "User IDs:", form.userIds);
    try {
        const token = sessionStorage.getItem("token");
        const payload = {
            hsReservation: form.hsReservation,
            roomIds: form.roomIds,
            userIds: form.userIds,
        };
        console.log('Payload to send:', payload);
        const response = await axios.post('/api/homestay/reservation/add', payload, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });
        console.log('booking succeed:', response.data);
        dialogFormVisible.value = false;
    } catch (error) {
        console.error('Failed to add reservation:', error);
    }
};

const totalImage = ref(0)
const images = ref([])
const getImages = async () => {
  const roomParams = {
    id: "",
    imageDesc: "",
    image: "",
    roomId: props.roomData.id
  }
  const token = sessionStorage.getItem("token");
  axios.get('/api/homestay/roomImage/list' ,{
    params: roomParams,
    headers: {
      'Authorization': 'Bearer ' + token
    }
  }).then(response => {
    const data = response.data
    images.value = data.rows
    totalImage.value = data.total
    console.log(data)
  }).catch(error =>{
    console.log(error)
  })
}

getImages();
getMyInfo();
</script>



<template>
    <div class="page-container">
        <el-row class="row-container">
            <el-col :span="5" class="column">
                <el-carousel height="25vh" :interval="3000" arrow="hover" v-if="totalImage !== 0" style="width: 35vh;">
                  <el-carousel-item v-for="image in images">
                    <img :src="image.image" alt="room image" />
                  </el-carousel-item>
                </el-carousel>
                <img src="/images/pic4.png" alt="Room Information" style="width: 35vh; height: 25vh;" v-if="totalImage === 0">
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
                            <el-form-item label="Name" :label-width="200">
                                <el-input v-model="form.userName" autocomplete="off" />
                            </el-form-item>
                            <el-form-item label="Phone Number" :label-width="200">
                                <el-input v-model="form.phoneNum" autocomplete="off">
                                </el-input>
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
