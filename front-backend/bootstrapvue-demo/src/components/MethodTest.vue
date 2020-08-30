<template>
  <div>
    <b-container>
      <b-row>
        <b-button size="sm" class="my-2 my-1 btn-info" @click="hello()">hello</b-button>
      </b-row>
    </b-container>
  </div>

</template>

<script>
  export default {
    name: "MethodTest",
    data() {

    },
    methods: {
      hello(){
        alert("123")
      }
    }
  }
</script>

<style scoped>

</style>
