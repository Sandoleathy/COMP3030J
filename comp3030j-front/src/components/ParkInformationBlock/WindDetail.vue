<template>
    <div style="width: 100%;">
        <el-row justify="space-between">
            <el-col :span="20">
                Wind Turbine
            </el-col>
            <el-col :span="4">
                <span>Manage Device: </span>
                <el-switch v-model="isStart"/>
            </el-col>
        </el-row>
    </div>
    <el-container direction="horizontal" class="container1">
        <el-main>
            <table border="1px">
                <tr>
                    <td>
                        <table border="1px">
                            <tr>
                                <td>
                                    Electric current: {{ I }}
                                </td>
                                <td>
                                    Voltage: {{ U }}
                                </td>
                            </tr>
                            <tr>
                                <td rowspan="2">
                                    ICON
                                </td>
                                <td>
                                    Torque: {{ torque }}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Rotational speed: {{ rpm }}
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table border="1px">
                            <tr>
                                <td>
                                    Generating capacity: {{ kwh }}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Carbon emission reduction: {{ reduceCarbon }}
                                </td>  
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </el-main>
    </el-container>
        

    
</template>
<script setup>
import { ElContainer, ElMain, ElHeader, ElMessage, ElRow, ElCol, ElSwitch } from 'element-plus';
import { ref } from 'vue'

const rpm = ref(0)
const kwh = ref(0)
const reduceCarbon = ref(0)
const I = ref(0)
const U = ref(0)
const torque = ref(0) //扭矩
const isStart = ref(true)

</script>
<style scope>
.container1{
    border-radius: 5px;
    width: 100%;
    height: 400px;
    box-shadow: 2px 2px 5px #888888;
}
table{
    font-size: 20px;
    height: 100%;
    width: 100%;
    text-align: center;
}
</style>