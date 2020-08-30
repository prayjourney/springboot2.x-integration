<template>
  <div>
    <div class="container">
      <div class="row col col-10 offset-1">
        <b-nav tabs>
          <b-nav-item active>Active</b-nav-item>
          <b-nav-item>Link</b-nav-item>
          <b-nav-item>Another Link</b-nav-item>
          <b-nav-item disabled>Disabled</b-nav-item>
        </b-nav>
      </div>
      <!-- 充满的所占页面的一个导航 -->
      <span class="lead text-info text-center"> 充满的所占页面的一个导航 </span>
      <div>
        <b-nav tabs fill>
          <b-nav-item active>首页</b-nav-item>
          <b-nav-item>新闻</b-nav-item>
          <b-nav-item>故事</b-nav-item>
          <b-nav-item disabled>关于</b-nav-item>
        </b-nav>
      </div>
    </div>

  </div>

</template>

<script>
  export default {
    name: "NavComponent"
  }
</script>

<style scoped>

</style>
