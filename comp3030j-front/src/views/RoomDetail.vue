<script setup>
import {onMounted,ref} from "vue";
import { useI18n } from 'vue-i18n';

import { useRouter } from 'vue-router';
import axios from "axios";

const { t } = useI18n();
const router = useRouter();
const roomId = ref(router.currentRoute.value.query.roomId);

function goBack() {
    router.push('reservation');
}

const totalImage = ref(0)
const images = ref([])
const getImages = async () => {
  console.log(roomId.value)
  const roomParams = {
    id: "",
    imageDesc: "",
    image: "",
    roomId: roomId.value
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
getImages()
</script>

<template>
    <div class="page-container">
        <el-carousel trigger="click" height="100vh" class="picture">
            <el-carousel-item v-for="image in images">
                <img :src="image.image" alt="carousel image" style="width: 100vw; height: 100vh; object-fit: fill;">
            </el-carousel-item>
        </el-carousel>
        <button class="choose-room-btn" @click="goBack">{{ t('roomDetail.choose') }}</button>

    </div>
</template>


<style scoped>
.page-container {

    width: 100vw;
    height: 100vh;
    position: relative; /* 相对定位以便于按钮定位 */
    background-color: black;
}
.picture{
    margin: 30px auto;
    width: 90vw;
    height: 85vh;
}

.demonstration {
    color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}

.choose-room-btn {
    position: absolute;
    right: 20px;
    bottom: 20px;
    padding: 10px 20px;
    background-color: #409EFF; /* 蓝色背景 */
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.choose-room-btn:hover {
    background-color: #3366cc;
}
</style>
