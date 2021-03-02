# spring-security-jwt

#### 介绍
spring-security-jwt, 在整合了mybatisplus的基础上开始整合jwt


### 想的方案
1. 后端统一返回ResponseBody的格式，前端接收的是json字符串，如果某个操作成功，在data里面，返回要跳转的url，然后让前端控制跳转，只是配置/login， /error页面，其他的权限都在接口上面配置
2. 异步的button点击跳转，a标签的异步跳转，在异步跳转的过程之中，也就是使用ajax，加入header信息，把token放入header之中，后端完全不去处理跳转页面的事情，也就是都只返回Json格式的数据，接口方法上面带上权限要求，只是负责status code状态码和msg的返回，前端根据status code状态码和msg来跳转
3. 搞定a标签，button的异步跳转的问题


### 结论
1. 一般的请求方式，我们没有办法直接修改header的格式，只有ajax的方式，我们才可以修改header的信息，所以同步没法改，只有异步才能改。
2. 还有一种折中的方式，就是token信息，直接通过token参数的方式来传送，不要通过header传送了，比如，我们<a href="/hello">访问hello页面</a>，然后修改成<a href="/hello?token
=xxxxxx">访问hello页面</a>，这种形式，这样就不用修改header之中的信息了，但是感觉上，这种怪怪的，而且显示起来感觉不简洁。
3. 要是用jwt的验证方式，那就完全使用前后端分离，vue+springboot的技术方案，如果要是使用传统的session，那么就使用springboot+thymeleaf的技术方案。


### 访问与验证
所以，当前的这些操作，需要使用postman来操作，就如同下面的这些网址之中的例子体现的，访问方式
[http://docs.javaboy.org/springboot/security-jwt.html#_2-4-测试](http://docs.javaboy.org/springboot/security-jwt.html#_2-4-测试), 
[spring-security-jwt-guide](https://github.com/Snailclimb/spring-security-jwt-guide/), 
[Spring Security 整合 JWT](https://www.jianshu.com/p/57e50b2b1144), [Spring Security整合JWT，实现单点登录，So Easy~！](https://www
.jianshu.com/p/8bd4a6e27e7f), [Spring Security（一）：整合JWT](https://juejin.cn/post/6844903753074606087), 
[SpringSecurity整合JWT](https://www.cnblogs.com/hujunzheng/p/10287250.html)
#### 本项目验证的接口
登录创建token: https://docs.apipost.cn/view/3e25e63272e2f3f9#3922826
查看wall: https://docs.apipost.cn/view/3ad733421651e4b2#3922838
查看scenery: https://docs.apipost.cn/view/f871998d1e100bd9#3948520
查看thymeleaf: https://docs.apipost.cn/view/5e887c36e29a85e7#3948507


### 扩展阅读
[由前端登录验证，页面跳转，携带headers token引发的思考和尝试](https://www.cnblogs.com/southday/p/10885235.html)


### 深入一点
```java
/**
 * @question: 原先使用的登录成功之后跳转的页面, successForwardUrl("/hello"), 跳转到/hello对应的页面
 * 现在登录陈工使用的是successHandler处理, 返回了一个token字符串, 所以就不会跳转页面了, 所以前端拿到的是字符串, 前端需要自己处理跳转。
 * ①如果使用window.location.href="/hello", 没有传入token, 又会被拦截, 就出现了很大的问题, 所以不要使用浏览器测试, ②使用postman登录,
 * 能不能访问, 这种情况下是要查看返回的json而不是跳转的页面, 因为此时, 后端已经不会转发页面了,③所以后端纯粹负责权限登录的验证, 全部返回json,
 * 前端根据json结果, 自主跳转页面。
 */
        ...
        http.formLogin()
            .usernameParameter("username")
            .passwordParameter("password")
            .loginPage("/index")
            // 原先使用的登录成功之后跳转的页面
            // .successForwardUrl("/hello")
            // 现在登录成功使用的是successHandler处理, 返回了一个token字符串, 所以就不会跳转页面了
            .loginProcessingUrl("/login").successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
            .and()
            // 授权的拦截器和异常处理器, token的解析, 获取权限
            // jwtAuthorizationFilter在UsernamePasswordAuthenticationFilter之前执行，查看有无token和解析token
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        ...
```
