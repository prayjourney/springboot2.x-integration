import Vue from 'vue'
import Router from 'vue-router'
import Home from "../components/Home"
import Teacher from "../components/Teacher";
import Student from "../components/Student";
import TeacherAdd from "../components/TeacherAdd";
import StudentAdd from "../components/StudentAdd";
import TeacherUpdate from "../components/TeacherUpdate";
import StudentUpdate from "../components/StudentUpdate";

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', redirect: '/home'},
    {path: '/home', name: 'home', component: Home},
    {
      path: '/teacher', name: 'teacher', component: Teacher,
      children: [
        // 子路由不用加'/', 否则的话就不会展示相应的页面了，https://www.jianshu.com/p/7d6c716d8eb8
        {path: 'add', name: 'teacheradd', component: TeacherAdd},
        {path: 'update', name: 'teacherupdate', component: TeacherUpdate}
      ]
    },
    {
      path: '/student', name: 'student', component: Student,
      children: [
        {path: 'add', name: 'studentadd', component: StudentAdd},
        {path: 'update', name: 'studentupdate', component: StudentUpdate}
      ]
    }
  ]
})
