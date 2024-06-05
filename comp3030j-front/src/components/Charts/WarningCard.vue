<template>
    <el-container>
      <el-header>
        <p class="title"> {{t('weatherInfo.WarningTitle')}} </p>
      </el-header>
      <el-main>
        <p> {{t('weatherInfo.updateTimeText')}} {{ updateTime }}</p>
        <template v-if="warnings.length === 0">
          <el-alert :title="t('weatherInfo.noWarning')" type="info" :closable="false"></el-alert>
        </template>
        <template v-else>
          <el-alert
              v-for="(warning, index) in warnings"
              :key="index"
              :title="warning.title"
              :type="warning.type"
              :closable="false"
              show-icon>
            <p>{{ warning.message }}</p>
          </el-alert>
        </template>
      </el-main>
    </el-container>
</template>
<script setup>
import axios from '@/axios'
import { onMounted, ref } from 'vue'
import {useI18n} from "vue-i18n";
const { t } = useI18n();

let location = "117.13,40.29";
let key = "76cbdc094c49493b9a1c3f7f6d659ff4";

let updateTime = ref("")
let warnings = ref([])

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
        warnings.value = data.warning
        updateTime.value = data.updateTime
        //console.log(data)
    }).catch(error => {
        console.log(error)
    })
}
</script>
<style scoped>
.title{
  font-size: 28px;
  font-weight: bolder;
}
</style>