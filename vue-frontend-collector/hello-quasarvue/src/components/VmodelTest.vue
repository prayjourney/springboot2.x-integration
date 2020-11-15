<template>
    <div class="vmodel">
        <!-- 绑定了方法，改变 -->
        <textarea v-model="poem" @click="print" />
    </div>
</template>

<script>
export default {
    name: "VmodelTest",
    data() {
        return {
            poem: "helloworld",
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
