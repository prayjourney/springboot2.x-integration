<template>
  <div>
    <h1>欢迎来到本网站</h1>
    <Footer></Footer>
  </div>
</template>

<script>
  import Footer from "./Footer";

  export default {
    name: "Home",
    components: {
      // 注册组件
      Footer: Footer
    }
  }
</script>

<style scoped>

</style>
