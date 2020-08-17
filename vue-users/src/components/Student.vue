<template>
  <div>
    <h1>学生页面</h1>

    <table style="border: 1px salmon inset;margin-left: 45%">
      <tr>
        <th>id</th>
        <th>学生姓名</th>
        <th>年龄</th>
        <th>邮箱</th>
        <th>操作</th>
      </tr>
      <tr v-for="s in students">
        <td>{{ s.id }}</td>
        <td>{{ s.name }}</td>
        <td>{{ s.age}}</td>
        <td>{{ s.email }}</td>

        <td>
          <a href="/#/student/add" class="btn btn-success">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="" class="btn btn-danger"> 删除 </a>
        </td>
      </tr>
    </table>
    <router-view/>
    <Footer></Footer>
  </div>
</template>

<script>
  import Footer from "./Footer";

  export default {
    name: "Student",
    components: {
      // 注册组件
      Footer: Footer
    },
    data() {
      return {
        students: [
          {id: 1, name: "妖二山", age: 22, email: "123@ww.com"},
          {id: 2, name: "扣扣哈", age: 32, email: "hellowrold@qq.com"},
          {id: 3, name: "麻花", age: 24, email: "mahutend@qq.com"},
          {id: 4, name: "trucy", age: 26, email: "trucy@tom.com"},
          {id: 5, name: "杨小过", age: 12, email: "winderful@126.com"}
        ]
      }
    },
    methods: {
      saveTeacher(student) {
        this.$http.post('http://localhost:8988/bootvue/student/add', student).then(resp => {
          console.log(resp.data);
          if (1 == resp.data) {
            console.log("success")
          }
        }).catch(function (err) {
          console.log(err)
        })
      },
      reset() {
        this.teacher = "";
      }
    },
    created() {
      this.$http.get('http://localhost:8988/bootvue/student/all').then(resp => {
        this.students = resp.data
        console.log(resp.data)
      }).catch(reason => {
        console.log(reason)
      })
    }
  }
</script>

<style scoped>

</style>
