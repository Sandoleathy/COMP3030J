import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'  //引入element ui
import 'element-plus/dist/index.css'
import axios from 'axios';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// @ts-ignore
import store from './store'
import plugins from './plugins' // plugins

import SvgIcon from '@/components/SvgIcon/index.vue'
import elementIcons from '@/components/SvgIcon/svgicon.js'
import { useDict } from '@/utils/dict.js'
import { parseTime, resetForm, addDateRange, handleTree, selectDictLabel, selectDictLabels } from '@/utils/ruoyi.js'
import { download } from '@/utils/request.js'
import i18n from '@/components/lang/index.js'

// 分页组件
import Pagination from '@/components/Pagination/index.vue'
// 自定义表格工具组件
import RightToolbar from '@/components/RightToolbar/index.vue'
// 富文本组件
import Editor from "@/components/Editor/index.vue"
// 文件上传组件
import FileUpload from "@/components/FileUpload/index.vue"
// 图片上传组件
import ImageUpload from "@/components/ImageUpload/index.vue"
// 图片预览组件
import ImagePreview from "@/components/ImagePreview/index.vue"
// 自定义树选择组件
import TreeSelect from '@/components/TreeSelect/index.vue'
// 字典标签组件
import DictTag from '@/components/DictTag/index.vue'
const app = createApp(App)
app.config.globalProperties.$axios = axios;

// 全局方法挂载
app.config.globalProperties.useDict = useDict
app.config.globalProperties.download = download
app.config.globalProperties.parseTime = parseTime
app.config.globalProperties.resetForm = resetForm
app.config.globalProperties.handleTree = handleTree
app.config.globalProperties.addDateRange = addDateRange
app.config.globalProperties.selectDictLabel = selectDictLabel
app.config.globalProperties.selectDictLabels = selectDictLabels

// 全局组件挂载
app.component('DictTag', DictTag)
app.component('Pagination', Pagination)
app.component('TreeSelect', TreeSelect)
app.component('FileUpload', FileUpload)
app.component('ImageUpload', ImageUpload)
app.component('ImagePreview', ImagePreview)
app.component('RightToolbar', RightToolbar)
app.component('Editor', Editor)


app.use(router)
app.use(store)
app.use(ElementPlus) //使用element ui
app.use(plugins)
app.use(elementIcons)
app.component('svg-icon', SvgIcon)
app.use(i18n)

app.mount('#app')

