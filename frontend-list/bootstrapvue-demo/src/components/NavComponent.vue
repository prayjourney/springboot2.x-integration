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

      <!-- 下拉菜单导航 -->
      <span class="lead text-info text-center"> 充满的所占页面的一个导航 </span>
      <div>
        <b-nav tabs fill>
          <b-nav-item active>首页</b-nav-item>
          <b-nav-item>新闻</b-nav-item>
          <b-nav-item-dropdown id="mynavitem" text="故事" toggle-class="nav-link-custom">
            <b-dropdown-item>呐喊</b-dropdown-item>
            <b-dropdown-item>狂人日记</b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item>阿Q正传</b-dropdown-item>
          </b-nav-item-dropdown>
          <b-nav-item disabled>关于</b-nav-item>
        </b-nav>
      </div>

      <!-- 下拉菜单导航, 循环获取菜单项 -->
      <span class="lead text-info text-center"> 充满的所占页面的一个导航 </span>
      <div>
        <b-nav tabs fill>
          <b-nav-item active>首页</b-nav-item>
          <b-nav-item>新闻</b-nav-item>
          <b-nav-item-dropdown id="mynavitem2" text="金庸小说" toggle-class="nav-link-custom">
            <b-dropdown-item v-for="x in booklist">{{x}}</b-dropdown-item>
          </b-nav-item-dropdown>
          <b-nav-item disabled>关于</b-nav-item>
        </b-nav>
      </div>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
    </div>

  </div>

</template>

<script>
  export default {
    name: "NavComponent",
    data(){
      return{
        booklist:[
          "倚天屠龙记","神雕侠侣","射雕英雄传","笑傲江湖"
        ]
      }
    }
  }
</script>

<style scoped>

</style>
