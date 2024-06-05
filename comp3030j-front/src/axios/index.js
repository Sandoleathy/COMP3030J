import axios from "axios";
import Cookies from 'js-cookie';
// 全局默认配置
axios.defaults.headers.common['Accept-Language'] = Cookies.get('language') || 'en';
export default axios