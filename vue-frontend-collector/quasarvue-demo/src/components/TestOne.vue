<template>
    <div class="hello">
        <h1>{{ msg }}</h1>
        <h1 v-text="info"></h1>
        <h1 v-html="text"></h1>
    </div>
</template>

<script>
    export default {
        name: "TestOne",
        data() {
            return {
                msg: "Welcome to Your Vue.js App",
                info: "你好啊，大哥！！！",
                text: "<strong>12313123</strong>"
            }
        },
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h1, h2 {
        font-weight: normal;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        display: inline-block;
        margin: 0 10px;
    }
    a {
        color: #42b983;
    }
</style>
