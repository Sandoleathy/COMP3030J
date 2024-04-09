import axios from 'axios'
import type { AxiosInstance } from 'axios';

//ts代码的写法和js有些不一样
const instance: AxiosInstance = axios.create({
    baseURL: 'localhost:1234',
    timeout: 10000,
});

export default instance;