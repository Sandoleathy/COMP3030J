<script setup>
import axios from 'axios'
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
import { listNotice } from "@/api/system/notice";
import { onMounted, ref } from "vue";
import {ElMessage} from "element-plus";

onMounted( () => {
  getNews()
})
const isLoading = ref(true)

const activeNum = ref(0)

const pageSize = ref(4);
const total = ref(0)
const noticeList = ref([])
const getNews = () => {
  isLoading.value = true
  listNotice().then(response => {
    noticeList.value = response.rows;
    noticeList.value = noticeList.value.reverse()
    total.value = response.total;
    // console.log(noticeList.value)
    isLoading.value = false;
  }).catch(error => {
    ElMessage.error("Error fetching news")
    isLoading.value = false;
  });
}
const currentPage = ref(1);
const paginatedNoticeList = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return noticeList.value.slice(start, end);
});

// 处理页码变化
const handlePageChange = (page) => {
  currentPage.value = page;
};
</script>

<template>
  <h1 class="minsu-head" style="margin-top: 60px">{{ t('firstPage.news') }}</h1>
  <div class="news-container">
    <el-skeleton :rows="5" animated v-if="isLoading"></el-skeleton>
    <el-card class="fixed-height-card">
      <div class="card-content" v-if="!isLoading">
        <el-collapse v-model="activeNum">
          <el-collapse-item :name="news.noticeId" v-for="news in paginatedNoticeList">
            <template #title>
              <span style="font-weight: bolder;">{{ news.noticeTitle }}</span>  &nbsp;&nbsp;
              Update Time:&nbsp;<span style="font-weight: normal;">{{ news.createTime }}</span>
            </template>
            <div v-html="news.noticeContent"></div>
          </el-collapse-item>
        </el-collapse>
      </div>
      <el-pagination
          layout="prev, pager, next"
          :total="total"
          :page-size="pageSize"
          :current-page.sync="currentPage"
          @current-change="handlePageChange"
      />
    </el-card>
  </div>
</template>

<style scoped>
h1{
  text-align: center;
  font-weight: bolder;
  color: white;
}
.news-container{
  border-radius: 5px;
  background-color: white;
  width: 100%;
}
.fixed-height-card {
  width: 100%; /* 卡片宽度 */
  overflow: hidden; /* 防止内容溢出 */
  display: flex;
  flex-direction: column;
  transition: height 0.5s ease;
}
.card-content {
  flex: 1;
  overflow-y: auto; /* 垂直方向滚动 */
  padding: 16px;
}
</style>