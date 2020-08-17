<template>
  <div class="container">
    <div class="col-md-10 col-md-offset-1">
      <h1>教师页面</h1>
      <table class="table table-bordered table-striped table-hover table-cell">
        <tr>
          <th>id</th>
          <th>教师姓名</th>
          <th>年龄</th>
          <th>教师学历</th>
          <th>操作</th>
        </tr>
        <tr v-for="t in teachers">
          <td>{{ t.id }}</td>
          <td>{{ t.name }}</td>
          <td>{{ t.age }}</td>
          <td>{{ t.grade }}</td>

          <td>
            <a href="#/teacher/add" class="btn btn-success btn-sm">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="" class="btn btn-danger btn-sm">删除</a>
          </td>
        </tr>
      </table>
      <router-view></router-view>
      <Footer></Footer>
    </div>
  </div>
</template>

<script>
  import Footer from "./Footer";

  export default {
    name: "Teacher",
    data() {
      return {
        teachers: [
          {id: 1, name: "张三", age: 22, grade: "本科"},
          {id: 2, name: "李四", age: 32, grade: "专科"},
          {id: 3, name: "Amy", age: 24, grade: "高职"},
          {id: 4, name: "关云长", age: 26, grade: "本科"},
          {id: 5, name: "杨过", age: 12, grade: "硕士"}
        ]
      }
    },
    components: {
      // 注册组件
      Footer: Footer
    },
    created() {
      this.$http.get('http://localhost:8988/bootvue/teacher/all').then(resp => {
        this.teachers = resp.data
        console.log(resp.data)
      }).catch(reason => {
        console.log(reason)
      })
    }
  }
</script>

<style scoped>
  th {
    border: 1px;
    border-style: solid;
    text-align: center;
    border-color: black;
  }

  tr, td {
    border: 1px;
    border-style: solid;
  }
</style>
