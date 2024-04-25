<template>
    <canvas id="chart"></canvas>
</template>
<script>
import {Chart,registerables} from 'chart.js'

Chart.register(...registerables); //记得注册！教程里都没写！3.0版本以上的chart.js需要手动注册控制器！
export default{
    data(){
        
    }
}
</script>