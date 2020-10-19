<template>
  <div class="container">
    <div class="col-md-10 col-md-offset-1">
      <h2>学生页面</h2>
      <table class="table table-bordered table-striped table-hover">
        <tr class="tr-m">
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
            <!-- 字体小图标设置颜色： https://www.itranslater.com/qa/details/2128961776657105920 -->
            <a href="/#/student/add" class="glyphicon glyphicon-plus" title="添加学生"
               style="color: lightgreen"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <a v-bind:href="'/#/student/update?id=' + s.id" class="glyphicon glyphicon-refresh" title="更新学生"
               style="color: deepskyblue"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0);" class="glyphicon glyphicon-remove warning" title="删除学生"
               @click="deleteStudentById(s.id)" style="color: red"/>&nbsp;&nbsp;&nbsp;&nbsp;
          </td>
        </tr>
      </table>
      <router-view/>
      <Footer></Footer>
    </div>
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
      findAll() {
        this.$http.get('http://localhost:8988/bootvue/student/all').then(resp => {
          this.students = resp.data
          console.log(resp.data)
        }).catch(reason => {
          console.log(reason)
        })
      },
      deleteStudentById(id) {
        this.$http.get('http://localhost:8988/bootvue/student/delete?id=' + id).then(resp => {
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
    created() {
      this.findAll();
    },
    watch: {
      $route: {
        handler: function (val, oldVal) {
          console.log(val);
          if (val.path == "/student") {
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
