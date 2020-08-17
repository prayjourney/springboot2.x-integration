<template>
  <div>
    <h2>添加学生</h2>
    <form action="" class="form-inline">
      <span>学生姓名：</span><input type="text" v-model="student.name" placeholder="杨过" name="name">
      <span>学生年龄：</span><input type="text" v-model="student.age" placeholder="22" name="name">
      <span>学生邮箱：</span><input type="text" v-model="student.email" placeholder="yanguo@88.com" name="name">
      <input type="submit" value="提交">&nbsp;&nbsp;<input type="reset" value="重置">
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
    }
  }
</script>

<style scoped>

</style>
