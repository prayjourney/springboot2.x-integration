<template>
  <div>
    <h2>添加老师</h2>
    <!-- 改成button，然后这儿就不乱跳了，感觉好像是只有formzi自己带了action的时候，input才设置为submit,如果是异步的方式，都设置成为button -->
    <form action="" class="form-inline">
      <span>老师姓名：</span><input type="text" name="name" placeholder="小龙女" v-model="teacher.name"></input>
      <span>老师年龄：</span><input type="text" name="age" placeholder="29" v-model="teacher.age"></input>
      <span>老师学历：</span><input type="text" name="grade" placeholder="本科" v-model="teacher.grade"></input>
      <input type="button" value="提交" class="btn btn-success btn-sm" @click="saveTeacher(teacher)">&nbsp;&nbsp;
      <input type="reset" value="取消" class="btn btn-primary btn-sm" @click="reset">
    </form>
  </div>
</template>

<script>
  export default {
    name: "TeacherAdd",
    data() {
      return {
        teacher: {name: '小龙女', age: '29', grade: '本科'}
      }
    },
    methods: {
      saveTeacher(teacher) {
        // console.log(teacher)
        // console.log(JSON.stringify(teacher))
        // 配置成为json
        this.$http.post('http://localhost:8988/bootvue/teacher/add', teacher, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        }).then(resp => {
          console.log(resp.data);
          // 添加如果成功，切换路由，跳转到/teacher, 也就是为了隐藏添加页面，然后在teacher之中监听url的变化，如果改变了，就重新获取数据
          if (1 == resp.data) {
            console.log("success")
            this.$router.push("/teacher")
          }
        }).catch(function (err) {
          console.log(err)
        })
      },

      reset() {
        this.teacher = "";
      }
    }
  }
</script>

<style scoped>
  form {
    font-size: 14px;
  }
</style>
