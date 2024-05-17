<template>
    <p>111</p>
</template>
<script setup>
import axios from 'axios'
import { onMounted, ref } from 'vue'

let location = "117.13,40.29";
let key = "76cbdc094c49493b9a1c3f7f6d659ff4";

onMounted( () => {
    getWarningInfo()
})

const getWarningInfo = () => {
    axios.get('https://devapi.qweather.com/v7/warning/now' , {
        params: {
            key: key,
            location: location,
            lang: 'en'
        }
    }).then(response => {
        const data = response.data
        console.log(response.data)
    }).catch(error => {
        console.log(error)
    })
}
</script>
<style scoped>

</style>