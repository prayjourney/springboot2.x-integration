import Vue from 'vue'
import Router from 'vue-router'
import Home from "../components/Home";
import MethodTest from "../components/MethodTest";
import NavComponent from "../components/NavComponent";
import FormComponent from "../components/FormComponent";
import CarouselComponent from "../components/CarouselComponent";
import TableComponent from "../components/TableComponent";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/method',
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
    },
    {
      path: '/carousel',
      name: 'CarouselComponent',
      component: CarouselComponent
    },
    {
      path: '/table',
      name: 'TableComponent',
      component: TableComponent
    }
  ]
})
