import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'

// https://vitejs.dev/config/
export default defineConfig({
  base: '/villa/',
  plugins: [
    vue(),
    vueJsx(),
  ],

  server: {
    port: 5173, // 开发服务器端口
    proxy: {
      '/villa/api': {
        //所有向后端项目的请求都直接使用/api，不要用localhost:8080，否则会触发跨域请求报错
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/villa\/api/, '') // 路径重写规则
      }
    }
  },

  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
