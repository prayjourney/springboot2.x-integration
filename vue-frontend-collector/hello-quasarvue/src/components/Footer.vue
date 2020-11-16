<template>
    <div>
        <h5 style="text-align: center; color: lightslategray" v-text="copy"></h5>
    </div>
</template>

<script>
export default {
    name: "Footer",
    // 子组件之中定义为props
    props: ["copy"],
    data() {
        return {
            // copy: "zgy版权所有 &copy; 2018-2020",
        }
    },
}
</script>
<style scoped>
</style>