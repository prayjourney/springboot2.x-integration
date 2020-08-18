<template>
  <div>
    <h2>更新老师</h2>
    <!-- 改成button，然后这儿就不乱跳了，感觉好像是只有formzi自己带了action的时候，input才设置为submit,如果是异步的方式，都设置成为button -->
    <form action="" class="form-inline">
      <span>id：</span> <input type="text" v-model="teacher.id" readonly></input>
      <span>姓名：</span><input type="text" v-model="teacher.name"></input>
      <span>年龄：</span><input type="text" v-model="teacher.age"></input>
      <span>学历：</span><input type="text" v-model="teacher.grade"></input>
      <input type="button" value="提交" class="btn btn-success btn-sm" @click="updateTeacher(teacher)">&nbsp;&nbsp;
      <input type="reset" value="取消" class="btn btn-primary btn-sm" @click="reset">
    </form>
  </div>
</template>

<script>
  export default {
    name: "TeacherUpdate",
    data() {
      return {
        teacher: {
          // 表示id属性一定有
          id: ""
        }
      }
    },
    methods: {
      updateTeacher(teacher) {
        // 配置成为json
        this.$http.post('http://localhost:8988/bootvue/teacher/update', teacher, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        }).then(resp => {
          console.log(resp.data);
          // 更新成功跳转到/teacher页面
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
      },

      getTeacherById() {
        this.$http.get('http://localhost:8988/bootvue/teacher/get?id=' + this.teacher.id).then(function (resp) {
          // TeacherUpdate.vue?bc81:52 Uncaught (in promise) TypeError: Cannot set property 'teacher' of undefined
          this.teacher = resp.data
        })
      }
    },
    // 此处就可以接收路由传参的id了
    created() {
      console.log("修改的老师的id: " + this.$route.query.id)
      this.teacher.id = this.$route.query.id;
      this.getTeacherById();
    }
  }
</script>

<style scoped>
  form {
    font-size: 14px;
  }
</style>
