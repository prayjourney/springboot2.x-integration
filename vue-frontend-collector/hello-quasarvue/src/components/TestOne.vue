<template>
    <div class="hello">
        <a href="#/">首页</a>
        <a href="#/testone">123123</a>
        <h1>{{ msg }}</h1>
        <h2 v-text="text"></h2>
        <h2 v-html="info"></h2>
        <input type="button" value="点击啊！" v-on:click="helloClick" />
        <input id="hello" type="button" value="点击啊1！" @click="helloClick" />
        <input id="hello2" type="button" value="点击啊2！" @click="clickMethod('你好')" />
        <input id="hello3" type="button" value="点击啊3！" @click="clickMethod('你好')" />
        <input id="hello4" type="button" value="点击啊4！" @click="changeShow()" />
        <h2 v-show="showinfo">你好啊</h2>
        <router-view/>
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
            showinfo: true,
            shouye: "首页",
            ceshi01: "test 01"
        }
    },
    methods: {
        helloClick() {
            alert("123")
        },
        clickMethod(name) {
            alert("你好, " + name)
        },
        changeShow() {
            this.showinfo = !this.showinfo
            console.log(this.showinfo)
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
