<script setup>
import { computed, defineProps, ref, watchEffect, reactive, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
const { t } = useI18n();

const loading = ref(true)

const router = useRouter();
const props = defineProps({
    roomData: Object,
    dateRange: Array,
    buildingType: String,
    guestCount: Number
});

const roomType = ref('');
const price = ref('');
const bedType = ref('');
const smoking = ref('');
const breakfastIncludes = ref('');
const gridData = ref([]);
const buildingType=ref('');

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
    console.log("Guest Num", props.guestCount);
    roomType.value = props.roomData ? props.roomData.roomType : 'undefined';
    price.value = props.roomData ? props.roomData.roomPrice : '0';
    bedType.value = props.roomData && props.roomData.bedType ? props.roomData.bedType.toString() : 'unknown';
    buildingType.value=props.roomData && props.roomData.buildingType ? props.roomData.buildingType.toString() : 'unknown';

    if (props.roomData && props.roomData.buildingType) {
        fetchBuildingTypeData(props.roomData.buildingType);
    }
    if (props.dateRange && props.dateRange.length >= 2) {
        gridData.value = [
            {
                Start_Date: formatDateOnly(props.dateRange[0]),
                End_Date: formatDateOnly(props.dateRange[1]), // 退房时间
                // Building_Type: props.roomData.buildingType.toString(),
            },
        ];
        form.hsReservation.checkinTime = formatDate(props.dateRange[0]);  // 使用 formatDate 来格式化日期
        form.hsReservation.checkoutTime = formatDate(props.dateRange[1]);
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
    form.hsReservation.numberOfGuests = props.guestCount || 1;


});

const roomtype = computed(() => {
    if (!props.roomData || !props.roomData.roomType) {
        return t('roonItems.undefined'); // Make sure to define this key in your i18n files
    }
    // Assuming roomType is a predictable, controlled string like "single", "double", "suite"
    const key = `roonItems.${props.roomData.roomType}`;
    return t(key);
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
        smoking.value = response.data.data.allowSmoking === 1 ? t('roonItems.nonsmoking') : t('roonItems.smoking');
        loading.value = false;
    }).catch(error => {
        console.error('Failed to obtain building type data:', error);
        loading.value = false;
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
    if (bedType.value === "5") {
        return t('roonItems.single_bed');  // Assuming 'roonItems.single_bed' is defined in your localization files
    } else if (bedType.value === "1") {
        return t('roonItems.king_size_bed');  // Assuming 'roonItems.king_size_bed' is defined in your localization files
    } else if (bedType.value === "3") {
        return t('roonItems.many_king_size_bed');
    } else {
        return t('roonItems.unknown');  // Assuming 'roonItems.unknown' is defined in your localization files
    }
});

const building = computed(() => {
    if (buildingType.value === '1') {
        return t('roonItems.economic');  // Assuming 'roonItems.single_bed' is defined in your localization files
    } else if (buildingType.value === '2') {
        return t('roonItems.deluxe');  // Assuming 'roonItems.king_size_bed' is defined in your localization files
    } else if (buildingType.value === '3') {
        return t('roonItems.family');
    } else if (buildingType.value === '4') {
        return t('roonItems.mountain');
    }else {
        return t('roonItems.unknown');  // Assuming 'roonItems.unknown' is defined in your localization files
    }
});

function goToRoomDetails() {
    router.push({
        path: '/roomdetails',
        query: {roomId: props.roomData.id}
    });
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
    loading.value=true;
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
        loading.value=false;
        ElMessage({
            message: t('roonItems.booksuccess'),
            type: 'success',
            duration: 5000
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

const getBuildingTypeIcon = () => {
    if (props.roomData.buildingType === 3) {
        return `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" style="cursor: pointer; width: 20px; height: 20px; fill: currentColor;"><path fill="currentColor" d="M512 128 128 447.936V896h255.936V640H640v256h255.936V447.936z"></path></svg>`;
    }else {
        return `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" style="cursor: pointer; width: 20px; height: 20px; fill: currentColor;">
            <path fill="currentColor" d="M288 320a224 224 0 1 0 448 0 224 224 0 1 0-448 0m544 608H160a32 32 0 0 1-32-32v-96a160 160 0 0 1 160-160h448a160 160 0 0 1 160 160v96a32 32 0 0 1-32 32z"></path>
            </svg>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" style="cursor: pointer; width: 20px; height: 20px; fill: currentColor;">
            <path fill="currentColor" d="M288 320a224 224 0 1 0 448 0 224 224 0 1 0-448 0m544 608H160a32 32 0 0 1-32-32v-96a160 160 0 0 1 160-160h448a160 160 0 0 1 160 160v96a32 32 0 0 1-32 32z"></path>
            </svg>`;
    }
};

import { ElMessage } from 'element-plus';

const openDialogAndCheckDates = () => {
    // Check if the dates in gridData are valid
    const hasInvalidDates = gridData.value.some(item => item.Start_Date === 'none' || item.End_Date === 'none');
    if (hasInvalidDates) {
        // Display an error message if dates are invalid
        ElMessage.error(t('roonItems.dateempty'));
        // Close the dialog
        dialogFormVisible.value = false;
    } else {
        // Open the dialog if dates are valid
        dialogFormVisible.value = true;
    }
}



getImages();
getMyInfo();
</script>



<template>
    <div class="page-container" v-loading="loading" element-loading-background="rgba(122, 122, 122, 0.2)">
        <el-row class="row-container">
            <el-col :span="5" class="column">
                <img :src="images[0].image" alt="room image" v-if="totalImage !== 0"/>
                <img src="/images/pic4.png" alt="Room Information" style="width: 35vh; height: 25vh;" v-if="totalImage === 0">
                <div>
                    <el-link @click="goToRoomDetails"> {{ t('roonItems.view') }}</el-link>
                </div>
            </el-col>
            <el-col :span="4" class="column">
                <div>
                    <h2>{{roomtype}}</h2>
                    <h4>{{bedtype}}</h4>

                    <h4>{{t('roonItems.window')}}</h4>
                    <h4>{{smoking}}</h4>
                </div>
            </el-col>
            <el-col :span="2" class="column">
                <div class="icons-container">
                    <div v-html="getBuildingTypeIcon()"></div>

                </div>
            </el-col>

            <el-col :span="3" class="column">
                <h3>{{t('roonItems.include')}} {{ breakfastIncludes }} {{ t('roonItems.breakfast') }}</h3>
            </el-col>
            <el-col :span="4" class="column">
                <h3>{{ t('roonItems.free') }}</h3>
            </el-col>
            <el-col :span="6" class="column">
                <div>
                    <h3>¥{{price}}</h3>
                    <el-tooltip
                    effect="dark"
                    :content="t('roonItems.bookTips')"
                    :disabled="dateRange.length !== 0">
                      <el-button plain @click="openDialogAndCheckDates" :disabled="dateRange.length === 0">{{t('roonItems.book')}}</el-button>
                    </el-tooltip>

                    <el-dialog v-model="dialogFormVisible" :title="t('roonItems.reservation')" width="650">
                        <el-form :model="form">
                            <el-table :data="gridData">
                                <el-table-column property="Start_Date" :label="t('roonItems.start')" width="150" />
                                <el-table-column property="End_Date" :label="t('roonItems.end')" width="150" />
                                <el-table-column :label="t('roonItems.roomtype')">
                                    <template #default="scope">
                                        {{ roomtype }}
                                    </template>
                                </el-table-column>
                                <el-table-column :label="t('roonItems.buildingtype')" >
                                    <template #default="scope">
                                        {{ building }}
                                    </template>
                                </el-table-column>
                            </el-table>
                            <div class="user">
                            <el-form-item :label="t('roonItems.name')" :label-width="200">
                                <el-input v-model="form.userName" autocomplete="off" />
                            </el-form-item>
                            <el-form-item :label="t('roonItems.phonenum')" :label-width="200">
                                <el-input v-model="form.phoneNum" autocomplete="off">
                                </el-input>
                            </el-form-item>
                            </div>
                        </el-form>
                        <template #footer>
                            <div class="dialog-footer">
                                <el-button @click="dialogFormVisible = false">{{t('roonItems.cancel')}}</el-button>
                                <el-button type="primary" @click="confirmReservation" v-loading="loading">
                                    {{t('roonItems.confirm')}}
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

/* Style for the reservation confirmation dialog */
.el-dialog__reservation-confirm {
    width: 650px; /* Fixed width as specified */
    margin: auto; /* Center the dialog horizontally */
    border-radius: 8px; /* Optional: adds rounded corners to the dialog */
    box-shadow: 0 4px 6px rgba(0,0,0,0.1); /* Optional: adds subtle shadow for depth */
}

/* Style for the form inside the dialog */
.el-dialog__reservation-confirm .el-form {
    padding: 20px;
    background-color: #fff; /* Optional: ensure a white background */
}

/* Style for buttons in the dialog footer */
.el-dialog__reservation-confirm .dialog-footer .el-button {
    margin-right: 10px;
}

.user{
    margin: 30px 90px 0px -50px ;
}



</style>
