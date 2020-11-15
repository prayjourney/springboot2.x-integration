<template>
    <div class="hello">
        <a href="#/"> 首页</a>
        <a href="#/testone">123123</a>
        <a v-bind:href="lianjie"> {{ceshi}}</a>
        <a v-bind:href="vmodel"> vmodel页面</a>
        <a href="#/newpage"> NewPage页面</a><br/>
        <input type="text" v-bind:placeholder="baidu" />
        <input type="text" v-bind:value="mashaji" /><br/>
        <h1>{{msg}}</h1>
        <h2>Essential Links</h2>
        <router-view />
    </div>
</template>

<script>
export default {
    name: "HelloWorld",
    // data, 和data()的作用是一样的，还是用这个data()比较好一点，初始化比较早
    // data : {
    //     msg: "Welcome to Your Vue.js App",
    //     shouye: "首页",
    //     ceshi01: "test 01",
    //     baidu: "baidu.com",
    // },
    data() {
        return {
            msg: "Welcome to Your Vue.js App",
            shouye: "首页",
            lianjie: "#/testone",
            ceshi: "测试01页面",
            baidu: "baidu.com",
            mashaji: "马杀鸡",
            vmodel: "#/vmodel"
        }
    },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
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
