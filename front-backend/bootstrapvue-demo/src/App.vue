<template>
  <div id="app">
    <img src="./assets/logo.png"><br/>
    <a href="#/">主页</a>
    <a href="#/nav">导航栏</a>
    <a href="#/form">表单提交</a>
    <a href="#/method">方法测试</a>
    <a href="#/carousel">轮播图</a>
    <a href="#/table">表格</a>
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
}
</style>
