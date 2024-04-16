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
const value4 = ref('')
const value3 = ref('')
const daysDifference = computed(() => {
    if (value3.value && value4.value) {
        return differenceInCalendarDays(new Date(value4.value), new Date(value3.value));
    }
    return 0; // 如果日期未完全选择，返回0
});
const value = ref('')

const options = [
    {
        value: 'A栋',
        label: '标准民宿',
    },
    {
        value: 'B栋',
        label: '豪华民宿',
    },
    {
        value: 'C栋',
        label: '观景民宿',
    },
    {
        value: 'D栋',
        label: '家庭民宿',
    },

]
</script>

<template>
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
                <el-col :span="8">
                    <div class="block">
                        <span class="demonstration">入住</span>
                        <el-date-picker
                            v-model="value3"
                            type="date"
                            placeholder="选择入住日期"
                        />
                    </div>
                </el-col>
                <el-col :span="8">
                    <div class="block days-difference">
                        {{ daysDifference }}天
                    </div>
                </el-col>
                <el-col :span="8">
                    <div class="block">
                        <span class="demonstration">退房</span>
                        <el-date-picker
                            v-model="value4"
                            type="date"
                            placeholder="选择退房日期"
                        />
                    </div>
                </el-col>
            </el-row>
        <span class="demonstration">Room：</span>
        <el-input-number v-model="num1" :min="1" :max="10" @change="handleChange" />
        <el-menu-item index="4">住客</el-menu-item>
        <el-input-number v-model="num2" :min="1" :max="10" @change="handleChange" />

            <div style="margin-top: 20px">
                <el-select v-model="value" placeholder="Room Type" style="width: 240px">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    />
                </el-select>
            </div>


        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="m795.904 750.72 124.992 124.928a32 32 0 0 1-45.248 45.248L750.656 795.904a416 416 0 1 1 45.248-45.248zM480 832a352 352 0 1 0 0-704 352 352 0 0 0 0 704"></path></svg>
    </el-menu>

</template>

<style scoped>
.block {
    display: flex;
    align-items: center;
    justify-content: center; /* Centers content within each column */
}

.days-difference {
    color: white; /* Ensures text is visible against dark menu background */
    text-align: center;
}

</style>