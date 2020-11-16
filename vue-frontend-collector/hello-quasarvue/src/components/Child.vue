<template>
    <div>
        <button v-on:click="change">修改父组件数据</button> : <span v-text="messageFromParent"></span>
    </div>
</template>

<script>
export default {
    name: "Child",
    // 通过props数组接收
    props: ["messageFromParent"],
    methods: {
        change: function () {
            // 调用this.$emit方法第一个参数是事件的名称，后面全部都是参数。
            // this.$emit方法其实是自定义了一个事件，通过这种方式来完成子组件向父组件传递消息。
            // changeParentData是事件名称，父组件之中v-on:绑定的就是这个事件的名称, 后面的参数目前还没发现用处...
            this.$emit("changeParentData", "change data by child")
            console.log("change data by child")
        },
    },
}
</script>
<style scoped>
</style>