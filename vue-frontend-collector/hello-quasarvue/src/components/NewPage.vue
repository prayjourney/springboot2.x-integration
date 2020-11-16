<template>
    <div>
        <head></head>
        <h3 v-text="solgan"></h3>
        <foot v-bind:copy="footerinfo"></foot>
    </div>
</template>

<script>
// 组件复用，产生了父子组件之间，数据传递的问题
import Header from "./Header.vue"
import Footer from "./Footer.vue"
export default {
    name: "NewPage",
    data() {
        return {
            solgan: "新的一天，要充满希望！",
            // 父子组件传递数据, 父组件之中绑定定义好的子组件之中的属性，使用父组件之中的data
            footerinfo: "Hello world！"
        }
    },
    // 注册组件
    components: {
        head: Header,
        foot: Footer
    },
}
</script>
<style scoped>
</style>