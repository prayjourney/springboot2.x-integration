<template>
  <div>
    <b-container>
      <b-row class="col col-10 offset-1">
        <b-button size="sm" class="btn btn-danger btn-block" @click="hello()">hello</b-button>
        <b-alert class="alert alert-warning col col-12" v-model="showonnot">
          <p class="mb-0">
            我是一个警告条框！！！
          </p>
        </b-alert>
      </b-row>

      <!-- 进度条 -->
      <b-progress class="col col-10 offset-1">
        <b-progress-bar value="30%" class="progress-bar progress-bar-striped" animated></b-progress-bar>
      </b-progress>

      <!-- 徽章，variant和class的作用一致，简洁一些-->
      <b-button variant="primary">
        提示 <b-badge variant="light">4</b-badge>
      </b-button>

    </b-container>
  </div>

</template>

<script>
  export default {
    name: "MethodTest",
    data() {
      return {
        showonnot: false
      }

    },
    methods: {
      hello() {
        if (this.showonnot == true) {
          this.showonnot = false
        } else {
          this.showonnot = true
        }
      }
    }
  }
</script>

<style scoped>

</style>
