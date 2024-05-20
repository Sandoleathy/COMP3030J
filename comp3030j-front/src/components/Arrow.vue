<template>
    <div class="wind-vane">
        <table style="width: 100%;height: 100%;font-size: 12px;" >
            <tr>
                <td></td>
                <td style="vertical-align: top;">North</td>
                <td></td>
            </tr>
            <tr>
                <td style="text-align: left;">West</td>
                <td></td>
                <td style="text-align: right;">East</td>
            </tr>
            <tr>
                <td></td>
                <td style="vertical-align: bottom;">South</td>
                <td></td>
            </tr>
        </table>
        <div :style="{ transform: `rotate(${degree}deg)` }" class="arrow"></div>
    </div>
    
  </template>
  
<script setup>
const props = defineProps({
    degree: Number
})
</script>
  
<style>
.arrow {
    width: 0;
    height: 0;
    border-left: 10px solid transparent; /* 左侧透明边框 */
    border-right: 10px solid transparent; /* 右侧透明边框 */
    border-top: 40px solid rgb(91, 91, 91); /* 底部实线边框，可以根据需要设置颜色 */
    position: absolute;
    top: 35%;
    left: 43%;
}
.wind-vane {
      width: 120px;
      height: 120px;
      border: 3px solid gray;
      border-radius: 50%; /* 圆形形状 */
      position: relative;
      overflow: hidden;
    }
</style>
  