<script lang="ts" setup>
import {computed, ref} from 'vue'
import {options} from "axios";
import { differenceInCalendarDays } from 'date-fns'  // 使用 date-fns 来计算日期差异

const activeIndex = ref('1')
const activeIndex2 = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}

const num1 = ref(1)
const num2 = ref(1)

const handleChange = (value: number) => {
    console.log(value)
}
const value = ref('');  // 假设这里是你选中的 building type 的值
const emits = defineEmits(['search']);  // 定义一个事件发射器
const handleIconClick = () => {
    console.log('Icon clicked, emitting search with building type and dates:', value.value,value1.value);
    emits('search', value.value,value1.value);
    // 在这里添加你想执行的动作，比如导航到另一个页面或打开一个对话框等
}

const value1 = ref('')
const size = ref<'default' | 'large' | 'small'>('default')
const daysDifference = computed(() => {
    // 确保 value1 有两个日期值
    if (value1.value.length === 2 && value1.value[0] && value1.value[1]) {
        return differenceInCalendarDays(new Date(value1.value[1]), new Date(value1.value[0]));
    }
    return 0;
});


const options = [
    {
        value: '1',
        label: 'Economic Room',
    },
    {
        value: '2',
        label: 'Deluxe Room',
    },
    {
        value: '3',
        label: 'Mountain Room',
    },
    {
        value: '4',
        label: 'Family Room',
    },

]
</script>

<template>
    <div class="page-container">
    <el-menu
            class="el-menu-demo"
            mode="horizontal"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            @select="handleSelect"
            :ellipsis="false"
    >
            <el-row>
                <el-col :span="24">

                        <div class="block">
                            <span class="demonstration">{{ daysDifference }} Day</span>
                            <el-date-picker
                                v-model="value1"
                                type="daterange"
                                range-separator="To"
                                start-placeholder="Start date"
                                end-placeholder="End date"
                                :size="size"
                            />
                        </div>

                </el-col>

            </el-row>
        <el-row >
                <div class="block">
                    <span class="demonstration">Room：</span>
                    <el-input-number v-model="num1" :min="1" :max="10" @change="handleChange" />

                </div>

        </el-row>
        <el-row >
                  <div class="block">
                      <span class="demonstration">Guest：</span>
                      <el-input-number v-model="num2" :min="1" :max="10" @change="handleChange" />
                  </div>


        </el-row>
        <el-row >
            <el-col :span="24">

            <div class="block">
                <span class="demonstration">Room Type:</span>
                <el-select v-model="value" placeholder="Room Type" style="width: 240px">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    />
                </el-select>
            </div>
            </el-col>
        </el-row>


        <div class="block">
            <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 1024 1024"
                @click="handleIconClick"
                style="cursor: pointer; width: 36px; height: 36px; fill: white;">
                <path d="m795.904 750.72 124.992 124.928a32 32 0 0 1-45.248 45.248L750.656 795.904a416 416 0 1 1 45.248-45.248zM480 832a352 352 0 1 0 0-704 352 352 0 0 0 0 704"></path>
            </svg>

        </div>


    </el-menu>
  </div>
</template>

<style scoped>
.page-container{
    margin: auto;
    padding-bottom: 30px;
    width: 95%;
}
.el-row:not(:last-child) {
    border-right: 2px solid #ccc; /* 灰色竖线 */
    position: relative; /* 确保 border-right 正确显示 */
}
.el-menu-demo{
    height: 100px;
    padding: 13px;
}
.block {
    display: flex;
    flex-direction: column; /* 子元素垂直堆叠 */
    align-items: center; /* 水平居中对齐子元素 */
    justify-content: center; /* 垂直居中子元素 */
    color: #f8f8f8;
    margin-left: 30px;
    margin-right: 20px;
}
.demonstration{
    margin-bottom: 15px;

}




</style>