<template>
    <div class="container">
        <el-container>
            <el-header class="header">
                <loginBar :bar-color="'#409EFF'"></loginBar>
            </el-header>
            <el-main class="main">
                <el-container>
                    <el-header>
                        <el-header>
                            <el-tabs v-model="pageNum">
                                <el-tab-pane :label="t('dataDetail.energy')" name='0'></el-tab-pane>
                                <el-tab-pane :label="t('dataDetail.weather')" name='2'></el-tab-pane>
                                <el-tab-pane :label="t('dataDetail.hotel')" name='3'></el-tab-pane>
                                <el-tab-pane :label="t('dataDetail.user')" name="4"></el-tab-pane>
                                <el-tab-pane :label="t('dataDetail.config')" name="config"></el-tab-pane>
                                <el-tab-pane :label="t('dataDetail.dept')" name="dept"></el-tab-pane>
                                <el-tab-pane :label="t('dataDetail.dict')" name="dict"></el-tab-pane>
                                <el-tab-pane :label="t('dataDetail.menu')" name="menu"></el-tab-pane>
                                <el-tab-pane :label="t('dataDetail.notice')" name="notice"></el-tab-pane>
                                <el-tab-pane :label="t('dataDetail.post')" name="post"></el-tab-pane>
                                <el-tab-pane :label="t('dataDetail.role')" name="role"></el-tab-pane>
                            </el-tabs>
                        </el-header>
                    </el-header>
                    <el-main>
                        <div v-if="pageNum ==='0'">
                          <div style="max-width: 100vw;">
                            <el-row>
                              <el-col :span="24" style="font-weight: bolder;font-size: 32px; text-align: center;color: black;">
                                {{ t('dataDetail.park') }}
                              </el-col>
                            </el-row>
                            <totalDetail></totalDetail>
                          </div>

                            <el-collapse v-model="activeNames" >
                                <el-collapse-item name="1" title="Wind Turbine">
                                    <windDetail></windDetail>
                                </el-collapse-item>
                                <el-collapse-item name="2" title="Solar Panel">
                                    <solarDetail></solarDetail>
                                </el-collapse-item>
                                <el-collapse-item name="3" title="Hydraulic Generator">
                                    <waterDetail></waterDetail>
                                </el-collapse-item>
                            </el-collapse>
                        </div>
                        <div v-if="pageNum ==='2'">
                            <weatherInfo></weatherInfo>
                            <tempretureCard></tempretureCard>
                            <warningCard></warningCard>
                        </div>
                        <div v-if="pageNum ==='3'">
                            <hotelInfo></hotelInfo>
                            <el-divider></el-divider>
                        </div>
                        <div v-if="pageNum ==='4'">
                            <userIndex></userIndex>
                        </div>
                        <div v-if="pageNum === 'config'">
                            <system-config></system-config>
                        </div>
                        <div v-if="pageNum === 'dept'">
                            <system-dept></system-dept>
                        </div>
                        <div v-if="pageNum === 'dict'">
                            <system-dict></system-dict>
                        </div>
                        <div v-if="pageNum === 'menu'">
                            <system-menu></system-menu>
                        </div>
                        <div v-if="pageNum === 'notice'">
                            <system-notice></system-notice>
                        </div>
                        <div v-if="pageNum === 'post'">
                            <system-post></system-post>
                        </div>
                        <div v-if="pageNum === 'role'">
                            <system-role></system-role>
                        </div>
                    </el-main>
                </el-container>
            </el-main>
        </el-container>
    </div>
    
</template>

<script setup>
import { ElContainer, ElHeader, ElMain, ElDivider,ElTabs, ElTabPane, ElCollapse, ElCollapseItem} from 'element-plus';
import loginBar from '../components/LoginBar.vue';
import windDetail from '../components/ParkInformationBlock/WindDetail.vue';
import solarDetail from '../components/ParkInformationBlock/SolarDetail.vue';
import weatherInfo from '../components/WeatherOverview.vue';
import waterDetail from '../components/ParkInformationBlock/WaterDetail.vue';
import tempretureCard from '../components/Charts/TemperatureCard.vue';
import warningCard from '../components/Charts/WarningCard.vue';
import hotelInfo from '../components/HotelManagement/HotelInfo.vue';
import userIndex from '../components/user/index.vue'
import systemConfig from '@/components/system/config/index.vue';
import systemDept from '@/components/system/dept/index.vue';
import systemDict from '@/components/system/dict/index.vue';
import systemMenu from '@/components/system/menu/index.vue';
import systemNotice from '@/components/system/notice/index.vue';
import systemPost from '@/components/system/post/index.vue';
import systemRole from '@/components/system/role/index.vue';
import totalDetail from '@/components/ParkInformationBlock/TotalDetail.vue'
import { connectWebSocket } from '@/chartData/EnergyDetail.ts'
import { ref, onMounted, onUnmounted } from 'vue'
import { useI18n } from 'vue-i18n';
const { t } = useI18n();

const pageNum = ref('0')
const activeNames = ref([])
let socket
const socketURL = 'ws://localhost:8080/statistics/ws'

onMounted(() => {
  socket = connectWebSocket(socketURL);
})

onUnmounted(() => {
  socket.close()
})
</script>

<style scoped>
 html,body,#app{
        height:100%;
        margin: 0px;
        padding: 0px;
}
.container {
    min-width: 100vw;
    width: 90%;/* 根据实际内容调整 */
    background: white
}
.header{
    padding: 0px;
    margin: 0;
    width: 100%;
}
.main{
    max-width: 100%
}
</style>