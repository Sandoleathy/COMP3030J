<template>
    <div class="temp-card">
        <p>{{ temperature }}°C</p>
        <p>{{ feelTemp }}°C</p>
        <p>{{ weatherText }}</p>
    </div>
</template>
<script setup>
import axios from 'axios'
import { ref, onMounted } from 'vue'

const temperature = ref(0)
const feelTemp = ref(0)
const weatherText = ref("")
</script>
<style scoped>
.temp-card{
    border-radius: 15px;
    background: linear-gradient( #87CEEB, rgb(255, 246, 174));
    padding: 10px;
}
</style>