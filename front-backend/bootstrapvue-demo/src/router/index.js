import Vue from 'vue'
import Router from 'vue-router'
import NavComponent from "../components/NavComponent";
import MethodTest from "../components/MethodTest";
import FormComponent from "../components/FormComponent";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MethodTest',
      component: MethodTest
    },
    {
      path: '/form',
      name: 'FormComponent',
      component: FormComponent
    },
    {
      path: '/nav',
      name: '',
      component: NavComponent
    }
  ]
})
