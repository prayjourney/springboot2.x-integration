<template>
  <div>
    <h2>添加学生</h2>
    <form action="" class="form-inline">
      <span>学生姓名：</span><input type="text" v-model="student.name" placeholder="杨过" name="name">
      <span>学生年龄：</span><input type="text" v-model="student.age" placeholder="22" name="name">
      <span>学生邮箱：</span><input type="text" v-model="student.email" placeholder="yanguo@88.com" name="name">
      <!-- 用了vue, input就不要使用submit形式了, 都使用button, 绑定事件响应即可 -->
      <input type="button" class="btn btn-success btn-sm" value="提交" @click="saveStudent(student)" /> &nbsp;&nbsp;
      <input type="reset" class="btn btn-primary btn-sm" value="重置" @click="reset">
    </form>
  </div>

</template>

<script>
  export default {
    name: "StudentAdd",
    data() {
      return {
        student: {name: '杨过', age: '22', email: 'yangguo@88.com'},
      }
    },
    methods:{
      saveStudent(student) {
        // 配置成为json
        this.$http.post('http://localhost:8988/bootvue/student/add', student, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        }).then(resp => {
          if (1 == resp.data) {
            console.log("success")
            this.$router.push("/student")
          }
        }).catch(function (err) {
          console.log(err)
        })
      },

      reset() {
        this.student = "";
      }
    }
  }
</script>

<style scoped>
  form{
    font-size: 14px;
  }
</style>
