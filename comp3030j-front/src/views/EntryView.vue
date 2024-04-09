<template>
  <div class="navigation_bar" style="width: 100%;">
    <el-container>
      <el-header>
        <navigationBar></navigationBar>
      </el-header>
    </el-container>
  </div>
  
    
</template>
<script setup>
import api from '../api'
import {onMounted} from 'vue'
import navigationBar from '../components/MenuNavigationBar.vue'

//axios的get方法例子
const clickTest = () => {
    api.get('/users')
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });
}

//页面初始化时会先执行的代码
onMounted( () => {
    clickTest()
})
</script>
<style>

</style>