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
