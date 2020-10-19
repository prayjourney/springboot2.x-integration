<template>
  <div id="app">
    <img src="./assets/logo.png"><br/>
    <a href="#/home">主页</a>
    <a href="#/teacher">教师页面</a>
    <a href="#/student">学生页面</a>
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
    font-size: large;
  }
</style>
