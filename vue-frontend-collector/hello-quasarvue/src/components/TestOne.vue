<template>
    <div class="hello">
        <h1>{{ msg }}</h1>
        <h2 v-text="text"></h2>
        <h2 v-html="info"></h2>
        <input type="button" value="点击啊！" v-on:click="helloClick" />
        <input id="hello" type="button" value="点击啊！" @click="helloClick" />
        <input id="hello" type="button" value="点击啊！" @click="clickMethod('你好')" />
    </div>
</template>

<script>
export default {
    name: "TestOne",
    data() {
        return {
            msg: "Test One",
            text: "我有一个梦想！",
            info: "醒来吧！！！",
        }
    },
    methods: {
        helloClick() {
            alert("123")
        },
        clickMethod(name) {
            alert("你好, " + name)
        },
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
