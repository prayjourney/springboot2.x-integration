<template>
  <div>
    <h2>添加老师</h2>
    <form action="">
      <span>老师姓名：</span><input type="text" name="name" placeholder="小龙女" v-model="teacher.name"></input>
      <span>老师年龄：</span><input type="text" name="age" placeholder="29" v-model="teacher.age"></input>
      <span>老师学历：</span><input type="text" name="grade" placeholder="本科" v-model="teacher.grade"></input>
      <input type="submit" value="提交" style="margin: 10px; " @click="saveTeacher(teacher)">
      <input type="reset" value="取消" style="margin: 10px; " @click="reset">
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
        this.$http.post('http://localhost:8988/bootvue/teacher/add', teacher).then(resp => {
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
    }
  }
</script>

<style scoped>

</style>
