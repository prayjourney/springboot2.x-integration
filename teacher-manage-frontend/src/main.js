// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
// 引入bootstrap css， 全局引入：https://blog.csdn.net/zhangvalue/article/details/104398936
import "bootstrap/dist/css/bootstrap.css"

Vue.prototype.$http=axios; // 修改内部的$http为axios, $http.get("") .post
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
