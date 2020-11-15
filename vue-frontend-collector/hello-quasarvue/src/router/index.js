import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import TestOne from '@/components/TestOne'
import VmodelTest from '../components/VmodelTest.vue'
import NewPage from '../components/NewPage.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/testone',
      name: 'TestOne',
      component: TestOne
    },
    // 添加路由，就可以访问了
    {
      path: '/vmodel',
      name: 'VmodelTest',
      component: VmodelTest
    },
    {
      path: '/newpage',
      name: 'NewPage',
      component: NewPage
    }
  ]
})
