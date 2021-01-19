## 返回视图或者是数据
### `@ResponseBody`注解后台方法的一些说明
有如下的这个方法，使用`@ResponseBody`修饰，返回一个字符串的方法签名形式。
```java
@ResponseBody
@GetMapping(value = "/kid/passlogin")
public String passLogin() {
    return "不需要验证";
}
```

1.如果前端是<font color="red">**直接调用(同步调用)**</font>，会跳转到这个接口方法的url路径，返回一个空页面，这个页面之中，写的是返回的内容。

```html
<a href="/kid/passlogin" name="直接的调用">直接的调用</a>
```
![](https://raw.githubusercontent.com/prayjourney/img-home/master/img/20210119163617.png)

2.如果前端是<font color="red">**异步调用**</font>，那么就把这个字符串，返回给异步回调函数，页面url没有变化，不会跳转。如果想要跳转，就需要前端控制跳转。

```javascript
<script>
    function checkHeader() {
        // 从localStorage之中获取
        let token = localStorage.getItem("Authorization")
        $.ajax({
            url: '/kid/passlogin',
            type: 'get',
            // dataType: 'json',
            headers: {
                "Accept": "text/plain; charset=utf-8",
                "Content-Type": "text/plain; charset=utf-8",
                "Authorization": token
            },
            success: function (data) {
                alert(data)； // 这个data就是不需要验证，页面的url不变
            },
            error: function (data) {
                console.log("error: " + data)
            }
        })
    }
</script>
```

### `@Controller`和`@RestController`和`@ResponseBody`
`@Controller`返回view页面，这个可以跳转，重定向页面`return "redirect:/"; return "forward:/hello";`
`@RestController`和`@ResponseBody`一样，同步返回一个空页面，页面上直接写上数据信息，异步返回规定的数据，不能重定向页面。
所以从这个角度来看，就要使用前端来管理页面的跳转，而后端只是负责业务处理和数据的返回，数据可以放在data之中返回，也可以放在header之中，但是对于`return "redirect:/"`或者是`return "forward:/hello";`这样的行为，在前后端完全分离的时候，就都让前端来做吧，后端专心处理业务和数据。