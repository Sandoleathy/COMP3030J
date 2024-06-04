import { createRouter, createWebHistory, type NavigationGuardNext } from 'vue-router'
import { ElMessage } from 'element-plus'
import axios from 'axios'

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
      component: () => import('../views/DataOverView.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('../views/AdminMenu.vue')
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
      component: () => import('../views/Login.vue')
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
    {
      path: '/error',
      name: 'Error',
      component: () => import('../views/Error.vue')
    },
    {
      path: '/RoomDetails',
      name: 'roomdetails',
      component: () => import('../views/RoomDetail.vue')
    },
    {
      path: '/employee',
      name: 'employee',
      component: () => import('../views/EmployeeView.vue')
    }
  ]
})

const whiteList = ['/login' , '/register' , '/']
const adminPages = ['/admin']

// router.beforeEach((to, from, next) => {
//   // 在这里进行权限验证、登录判断等操作
//   if (whiteList.indexOf(to.path) !== -1) {
//     // 在免登录白名单，直接进入
//     next()
//   }
//   else if(sessionStorage.getItem("token") == null){
//     //没有登录
//     ElMessage.warning("You have not logged in. Please login first")
//     next('/login')
//   }else{
//     //检查session是否过期
//     const token = sessionStorage.getItem("token")
//     //let isAuthenticated = false; // 标志位，表示用户是否已经通过验证
//
//     axios.get('api/system/user/profile', {
//       headers: {
//         'Authorization': 'Bearer ' + token
//     }
//     }).then(response => {
//       const data = response.data
//       if(data.code != 200){
//         //登录已过期
//         sessionStorage.clear()
//         ElMessage.warning("You have not logged in. Please login first")
//         next('/login')
//       }else{
//         //登录未过期
//         if(adminPages.indexOf(to.path) !== -1){
//           //目标页面为仅管理员访问页面
//           if(sessionStorage.getItem('isAdmin') == 'true'){
//             next()
//           }else{
//             console.log("user type wrong!")
//             //用户类型错误
//             next('/error')
//           }
//         }else{
//           next()
//         }
//       }
//     }).catch(error => {
//       console.error(error)
//     })
//   }
// })

router.afterEach(() => {
  // 跳转后的操作
})

router.beforeResolve((to, from, next) => {
  // 在导航被确认之前的操作
  next()
})

async function checkProfile(next: NavigationGuardNext , targetPath: string) {
  const token = sessionStorage.getItem("token")
  try {
    const response = await axios.get('api/system/user/profile', {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    });
    const data = response.data;
    console.log(data);
    if (data.code !== 200) {
      sessionStorage.clear();
      ElMessage.warning("You have not logged in. Please login first");
      next('/login');
    }else{
      console.log('gogogo');
      next(targetPath)
    }
  } catch (error) {
    console.error(error);
  }
}

export default router
