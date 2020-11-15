<template>
    <div class="vmodel">
        <!-- 绑定了方法，改变 -->
        <textarea v-model="poem" @click="print" /><br/>
        <!-- 样式修改 -->
        <label v-bind:style="style" v-text="text"></label>
        <!-- v-for的使用 -->
        <ul>
            <li v-for="user,index in users" v-bind:key="user.id">
                {{user.id}} -- {{user.name}} -- {{user.age}}
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: "VmodelTest",
    data() {
        return {
            poem: "helloworld",
            text: "v-for的使用",
            style: "font-size:40px",
            users: [
                { id: "1", name: "张三", age: "22" },
                { id: "2", name: "伊能静", age: "50" },
                { id: "3", name: "黄万里", age: "72" },
                { id: "4", name: "Jhon MItehds", age: "32" },
                { id: "5", name: "刘天娥", age: "18" },
            ],
        }
    },
    methods: {
        print() {
            // 改变的时候就打印出来
            console.log(this.poem)
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
