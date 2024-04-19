import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'  //引入element ui
import 'element-plus/dist/index.css'
import axios from 'axios';

const app = createApp(App)
app.config.globalProperties.$axios = axios;

app.use(router)
app.use(ElementPlus) //使用element ui

app.mount('#app')
