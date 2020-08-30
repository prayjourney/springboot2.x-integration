import Vue from 'vue'
import Router from 'vue-router'
import MyComponent001 from "../components/MyComponent001";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MyComponent001',
      component: MyComponent001
    }
  ]
})
