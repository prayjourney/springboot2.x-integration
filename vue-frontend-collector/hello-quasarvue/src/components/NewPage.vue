<template>
    <div>
        <head></head>
        <h3 v-text="solgan"></h3>
        <foot v-bind:copy="footerinfo"></foot>
        <child v-bind:messageFromParent="message" v-on:changeParentData="changeData('jkjkjk')"></child>
    </div>
</template>

<script>
// 组件复用，产生了父子组件之间，数据传递的问题
import Header from "./Header.vue"
import Footer from "./Footer.vue"
import Child from "./Child.vue"
export default {
    name: "NewPage",
    data() {
        return {
            solgan: "新的一天，要充满希望！",
            // 父子组件传递数据, 父组件之中绑定定义好的子组件之中的属性，使用父组件之中的data
            footerinfo: "Hello world！",
            message: "来自父组件的数据"
        }
    },
    // 注册组件
    components: {
        head: Header,
        foot: Footer,
        child: Child
    },
    methods: {
        changeData: function (data) {
            // this.message = "你好  a , 子组件的指令，修改了父组件的data！"
            this.message = data
        }
    },
}
</script>
<style scoped>
</style>