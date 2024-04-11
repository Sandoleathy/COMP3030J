<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
</script>

<template>
  <div id="app">
    <router-view />
  </div>
</template>

<!--<style scoped>-->
/*html, body {*/
/*    margin: 0;*/
/*    padding: 0;*/
/*    left: 0;*/
/*    width: 100%;*/
/*    height: 100%;*/
/*    box-sizing: border-box; !* 确保包括 padding 和 border 在内的总宽度等于 width *!*/
/*}*/

/*#app {*/
/*    width: 100%;*/
/*    min-height: 100vh;*/
/*    margin: 0;*/
/*    padding: 0;*/
/*    box-sizing: border-box;*/
/*}*/

/*header {*/
/*    line-height: 1.5;*/
/*    max-height: 100vh;*/
/*    width: 100%;*/
/*    margin: 0;*/
/*    padding: 0;!* 确保 header 宽度为 100% *!*/
/*    box-sizing: border-box;*/
/*}*/

/*.logo {*/
/*  display: block;*/
/*  margin: 0 auto 2rem;*/
/*}*/

/*nav {*/
/*  width: 100%;*/
/*  font-size: 12px;*/
/*  text-align: center;*/
/*  //margin-top: 2rem;*/
/*}*/

/*nav a.router-link-exact-active {*/
/*  color: var(--color-text);*/
/*}*/

/*nav a.router-link-exact-active:hover {*/
/*  background-color: transparent;*/
/*}*/

/*nav a {*/
/*  display: inline-block;*/
/*  padding: 0 1rem;*/
/*  //border-left: 1px solid var(--color-border);*/
/*}*/

/*nav a:first-of-type {*/
/*  border: 0;*/
/*}*/

/*//@media (min-width: 1024px) {*/
/*//    header {*/
/*//        display: flex;*/
/*//        place-items: center;*/
/*//        padding: 0;*/
/*//        left: 0;!* 去掉左右的留白 *!*/
/*//    }*/
/*//*/
/*//    .logo {*/
/*//        margin: 0 2rem 0 0;*/
/*//    }*/
/*//*/
/*//    header .wrapper {*/
/*//        display: flex;*/
/*//        place-items: flex-start;*/
/*//        flex-wrap: wrap;*/
/*//    }*/
/*//*/
/*//    nav {*/
/*//        text-align: left;*/
/*//        margin: 0; !* 去掉左边的留白 *!*/
/*//        font-size: 1rem;*/
/*//        padding: 1rem 0;*/
/*//        margin-top: 1rem;*/
/*//    }*/
/*//}*/

/*!*@media (max-width: 1024px) {*!*/
/*!*    header {*!*/
/*!*        padding: 0; !* 去掉左右的留白 *!*!*/
/*!*    }*!*/

/*!*    nav {*!*/
/*!*        margin: 0; !* 去掉左边的留白 *!*!*/
/*!*        //padding-top: 1rem; !* 保留顶部的间距 *!*!*/
/*!*    }*!*/

/*!*    #app {*!*/
/*//        padding-top: 0; !* 去掉顶部的留白 *!*/
/*//    }*/
////}
<!--</style>-->
