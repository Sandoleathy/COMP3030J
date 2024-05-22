import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
// @ts-ignore
import createVitePlugins from './vite/plugins'
import path from "path";

// https://vitejs.dev/config/
export default defineConfig({
  plugins:
    createVitePlugins("dev",true)
  ,

  server: {
    port: 5173, // 开发服务器端口
    proxy: {
      '/api': {
        //所有向后端项目的请求都直接使用/api，不要用localhost:8080，否则会触发跨域请求报错
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/api/, '') // 路径重写规则
      }
    }
  },

  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src')
    },
  // https://cn.vitejs.dev/config/#resolve-extensions
  extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']

  },

})
