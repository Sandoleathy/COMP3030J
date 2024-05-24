<template>
    <div>
        <h1>Reservation Details</h1>
        <table v-if="reservations.length > 0">
            <thead>
            <tr>
                <th>Reservation ID</th>
                <th>Check-in Date</th>
                <th>Check-out Date</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="reservation in reservations" :key="reservation.id">
                <td>{{ reservation.id }}</td>
                <td>{{ reservation.checkinTime }}</td>
                <td>{{ reservation.checkoutTime }}</td>
                <td>{{ reservation.reservationStatus }}</td>
            </tr>
            </tbody>
        </table>
        <p v-else>No reservations found.</p>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';

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
