<template>
  <div>
    <h2>更新学生</h2>
    <!-- 改成button，然后这儿就不乱跳了，感觉好像是只有formzi自己带了action的时候，input才设置为submit,如果是异步的方式，都设置成为button -->
    <form action="" class="form-inline">
      <span>id：</span> <input type="text" v-model="student.id" readonly></input>
      <span>姓名：</span><input type="text" v-model="student.name"></input>
      <span>年龄：</span><input type="text" v-model="student.age"></input>
      <span>邮箱：</span><input type="text" v-model="student.email"></input>
      <input type="button" value="提交" class="btn btn-success btn-sm" @click="updateStudent(student)">&nbsp;&nbsp;
      <input type="reset" value="取消" class="btn btn-primary btn-sm" @click="reset">
    </form>
  </div>
</template>

<script>
  export default {
    name: "StudentUpdate",
    data() {
      return {
        student: {
          // 表示id属性一定有
          id: ""
        }
      }
    },
    methods: {
      updateStudent(student) {
        // 配置成为json
        this.$http.post('http://localhost:8988/bootvue/student/update', student, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        }).then(resp => {
          console.log(resp.data);
          // 更新成功跳转到/student页面
          if (1 == resp.data) {
            console.log("success")
            this.$router.push("/student")
          }
        }).catch(function (err) {
          console.log(err)
        })
      },

      reset() {
        this.teacher = "";
      },

      getStudentById() {
        this.$http.get('http://localhost:8988/bootvue/student/get?id=' + this.student.id).then(function (resp) {
          this.student = resp.data
        })
      }
    },
    // 此处就可以接收路由传参的id了
    created() {
      console.log("修改的学生的id: " + this.$route.query.id)
      this.student.id = this.$route.query.id;
      this.getStudentById();
    }
  }
</script>

<style scoped>
  form {
    font-size: 14px;
  }
</style>
