<template>
    <div v-loading="loading">
        <el-table
            v-if="reservations.length > 0"
            :data="reservations"
            style="width: 100%;">
            <el-table-column
                prop="id"
                :label="t('order.id')"
                width="180">
            </el-table-column>
            <el-table-column
                prop="checkinTime"
                :label="t('roonItems.start')"
                width="180">
            </el-table-column>
            <el-table-column
                prop="checkoutTime"
                :label="t('roonItems.end')"
                width="180">
            </el-table-column>
            <el-table-column
                prop="numberOfRooms"
                :label="t('order.roomnum')"
                width="180">
            </el-table-column>
            <el-table-column
                prop="totalPrice"
                :label="t('order.price')"
                width="180">
            </el-table-column>
            <el-table-column
                prop="reservationStatus"
                :label="t('order.status')"
                width="180">
            </el-table-column>
        </el-table>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import axios from '@/axios';
import { ElTable, ElTableColumn } from 'element-plus';
import { useI18n } from 'vue-i18n';
const { t } = useI18n();

const loading = ref(true)

const reservations = ref([]);

onMounted(() => {
    fetchUserProfile();

});

function fetchUserProfile() {
    const token = sessionStorage.getItem("token"); // Assuming token is stored in sessionStorage
    console.log("Fetching user profile with token:", token);
    axios.get("/api/system/user/profile", {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(response => {
            console.log("User profile fetched successfully:", response.data);
            const userId = response.data.data.userId; // Adjust according to actual response structure
            fetchReservations(userId, token);
        })
        .catch(error => {
            console.error('Error fetching user profile:', error);
        });

}

function fetchReservations(userId, token) {
    console.log(`Fetching reservations for user ID: ${userId}`);
    axios.get(`/api/homestay/guest/queryByUserId/${userId}`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(response => {
            console.log("Reservations fetched successfully:", response.data);
            reservations.value = response.data.data;
            loading.value = false;// Ensure data structure aligns
        })
        .catch(error => {
            console.error('Error fetching reservations:', error);
        });


}
</script>



<style scoped>
table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}
</style>
