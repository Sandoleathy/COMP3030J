<template>
    <el-container>
      <el-header>
        <p class="title"> {{t('weatherInfo.WarningTitle')}} </p>
      </el-header>
      <el-main>
        <el-alert type="info" :closable="false"> {{t('weatherInfo.updateTimeText')}} {{ updateTime }}</el-alert>
        <template v-if="warnings.length === 0">
          <el-alert :title="t('weatherInfo.noWarning')" type="info" :closable="false"></el-alert>
        </template>
        <template v-else>
          <el-alert
              v-for="(warning, index) in warnings"
              :key="index"
              :title="warning.title"
              :type="'warning'"
              :closable="false"
              show-icon>
            <el-row>
              <el-col :span="20">
                <p>{{ warning.text }}</p>
              </el-col>
              <el-col :span="4">
                <el-button type="danger" style="width: 100%;height: 80%; padding: 0 10px 0 10px;white-space: normal;" @click="addWarningNotice(index)">
                  {{ t('weatherInfo.sendWarning') }}
                </el-button>
              </el-col>
            </el-row>
          </el-alert>
        </template>
      </el-main>
    </el-container>
</template>
<script setup>
import axios from '@/axios'
import { onMounted, ref } from 'vue'
import {useI18n} from "vue-i18n";
import { addNotice } from "@/api/system/notice";
import {ElMessage} from "element-plus";
const { t } = useI18n();

let location = "117.13,40.29";
let key = "76cbdc094c49493b9a1c3f7f6d659ff4";

let updateTime = ref("")
let warnings = ref([])



onMounted( () => {
    getWarningInfo()
})

const addWarningNotice = (index) => {
  console.log(warnings.value[0])
  const title = warnings.value[index].title
  const content = warnings.value[index].text
  const form = {
    noticeId: undefined,
    noticeTitle: title,
    noticeType: 2,
    noticeContent: content,
    status: "0"
  };
  addNotice(form).then(response => {
    console.log(response.data)
    ElMessage.success(t('weatherInfo.sendSuccessful'))
  }).catch(error => {
    console.log(error)
  })
}
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

        //模拟数据
        warnings.value = [{
          "id": "10102010020230403103000500681616",
          "sender": "上海中心气象台",
          "pubTime": "2023-04-03T10:30+08:00",
          "title": "上海中心气象台发布大风蓝色预警[Ⅳ级/一般]",
          "startTime": "2023-04-03T10:30+08:00",
          "endTime": "2023-04-04T10:30+08:00",
          "status": "active",
          "level": "",
          "severity": "Minor",
          "severityColor": "Blue",
          "type": "1006",
          "typeName": "大风",
          "urgency": "",
          "certainty": "",
          "text": "上海中心气象台2023年04月03日10时30分发布大风蓝色预警[Ⅳ级/一般]：受江淮气旋影响，预计明天傍晚以前本市大部地区将出现6级阵风7-8级的东南大风，沿江沿海地区7级阵风8-9级，请注意防范大风对高空作业、交通出行、设施农业等的不利影响。",
          "related": ""
        }]
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