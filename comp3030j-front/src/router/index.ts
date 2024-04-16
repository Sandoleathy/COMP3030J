import { createRouter, createWebHistory } from 'vue-router'

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
      // component: () => import('../views/ObjTest.vue')

    },
    {
      path: '/overview',
      name: 'overview',
      component: () => import('../views/ObjTest.vue')
    }
  ]
})
/*
// 路由守卫模板
router.beforeEach((to, from, next) => {
  // 在这里添加你的逻辑，例如检查用户权限
  if (to.name === 'about' && !userHasPermissionToViewAboutPage()) {
    // 如果用户没有权限访问 about 页面，则重定向到首页
    next('/');
  } else {
    // 如果用户有权限或者访问的是其他页面，则放行
    next();
  }
})
*/

export default router
