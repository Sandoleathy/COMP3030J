import { createRouter, createWebHistory } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/reservation',
      name: 'reservation',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/ReservationView.vue')
    },
    {
      //网站入口
      path: '/',
      name: 'entry',
      component: () => import('../views/MainView.vue')
    },
    {
      path: '/overview',
      name: 'overview',
      component: () => import('../views/DataDetail.vue')
    },
    {
      path: '/My',
      name: 'my',
      component: () => import('../views/MyView.vue')
    },
    {
      path: '/Data',
      name: 'data',
      component: () => import('../views/DataOverView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/Register.vue')
    },
    {
      path: '/Weather',
      name: 'weather',
      component: () => import('../views/WeatherInfo.vue')
    },
  ]
})

const whiteList = ['/login' , '/register' , '/']

router.beforeEach((to, from, next) => {
  // 在这里进行权限验证、登录判断等操作
  if (whiteList.indexOf(to.path) !== -1) {
    // 在免登录白名单，直接进入
    next()
  }
  else if(sessionStorage.getItem("token") == null){
    //没有登录
    ElMessage.warning("You have not logged in. Please login first")
    next('/login')
  }
  next() // 跳转到下一个路由
})

router.afterEach(() => {
  // 跳转后的操作
})

router.beforeResolve((to, from, next) => {
  // 在导航被确认之前的操作
  next()
})

export default router
