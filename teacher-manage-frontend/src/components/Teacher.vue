<template>
  <div class="container">
    <div class="col-md-10 col-md-offset-1">
      <h2>教师页面</h2>
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
            <a href="/#/teacher/add" class="glyphicon glyphicon-plus" title="添加老师"
               style="color: lightgreen"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <a v-bind:href="'/#/teacher/update?id=' + t.id" class="glyphicon glyphicon-refresh" title="更新老师"
               style="color: deepskyblue"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <!-- javascript:viod(0);阻止<a>标签默认行为 -->
            <!-- https://www.jianshu.com/p/8a2bd9792eec, https://blog.csdn.net/judas_jia/article/details/51166406 -->
            <a href="javascript:void(0);" class="glyphicon glyphicon-remove warning " title="删除老师"
               @click="deleteTeacherById(t.id)" style="color: red"/>&nbsp;&nbsp;&nbsp;&nbsp;
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
    methods: {
      // 查询所有
      findAll() {
        this.$http.get('http://localhost:8988/bootvue/teacher/all').then(resp => {
          this.teachers = resp.data
          console.log(resp.data)
        }).catch(reason => {
          console.log(reason)
        })
      },
      deleteTeacherById(id) {
        this.$http.get('http://localhost:8988/bootvue/teacher/delete?id=' + id).then(resp => {
          console.log(resp.data)
          // 删除成功后自动刷新
          if (1 == resp.data) {
            this.findAll();
          }
        }).catch(reason => {
          console.log(reason)
        })
      }
    },
    components: {
      // 注册组件
      Footer: Footer
    },
    created() {
      this.findAll();
    },
    // 用来监听，主要是检测url的变化
    watch: {
      $route: {
        handler: function (val, oldVal) {
          console.log(123)
          console.log(val);
          if (val.path == "/teacher") {
            this.findAll();
          }
        }
      }
    }
  }
</script>

<style scoped>
  table {
    font-size: medium;
  }

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
