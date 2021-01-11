function putongfashu() {
    return '三十六变';
}

function home() {
    return this.work
}

var sunwukong = {
    name: '孙悟空',
    age: 937,
    weapon: "金箍棒",
    fashu: function () {
        return '七十二变';
    }
}

var zhubajie = {
    name: '猪八戒',
    age: 567,
    weapon: "九齿钉耙",
    fashu: putongfashu
}

var shawujing = {
    name: '沙悟净',
    age: 736,
    work: '天宫',
    base: home

}

var tangsanzang = {
    name: '唐三藏',
    age: 36,
    work: "灵山",
    base: home
}

// this指向了shawujing, 如果不指向的话，那就会谁调用，指向谁
home().apply(shawujing, [])

// JSON字符串和对象的转换
var person = {
    name: "张三",
    age: 22,
    home: '北京'
}
// 对象转字符串
var pStr = JSON.stringify(person)

let pStr2obj = '{"name":"张三","age":22,"home":"北京"}'
// 字符串转对象
var pObj = JSON.parse(pStr)


