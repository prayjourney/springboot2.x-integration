### sp2-resources-setting
springboot对于资源的设置, resources, public, static, template等文件夹的作用。


###  Springboot的resources中各个文件夹的作用
顾名思义，就是整理下图所示的目录下各个文件夹的作用，以及通常习惯把哪些资源放在哪个路径下。
![](https://i.loli.net/2021/01/16/kSy7eugXWvtVDr1.png)
先说一下它们的优先级: `resources > static > public`，Spring Boot 默认将`/`所有访问映射到以下目录：`classpath:/META-INF/resources/, classpath:/resources/, classpath:/static/, classpath:/public/`，优先顺序也如此， 这个类是：`org.springframework.boot.autoconfigure.web.ResourceProperties`之中。

```java
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
      "classpath:/resources/", "classpath:/static/", "classpath:/public/" };

/**
 * Locations of static resources. Defaults to classpath:[/META-INF/resources/,
 * /resources/, /static/, /public/].
 */
private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
```



####  下面说一下各个目录的作用
1.public目录
优先级最低, 一般放一些公共资源
2.resources目录
放一些upload 上传的文件
3.static目录
静态页面放在static下，比如说一些图片. 首页(index.html)有时也放在static里面
4.templates目录
动态页面放在templates下, 只能通过controller才能访问到该目录!(和原来的WEB-INF差不多)，里面放thymeleaf模版的页面，在application.properties之中有配置前后缀，整个的页面，需要通过controller提供的方法来访问，所以这就需要提供到每个功能的首页的xxx/index，xxx/，的方法，用于跳转到页面。

```yaml
# 设置thymeleaf的前后缀
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.cache=false
spring.thymeleaf.mode=HTML
spring.thymeleaf.enabled=true
```
动态模版都放在这个文件夹之中。如果定义为如下的模式，name就只能在static目录下去访问动态页面了，效果是一样的，但是所有的页面就要都放到static下面，这样才能保证controller的请求，跳转到正确的页面，否则如果定义如下面，而我们的动态页面放到了templates文件夹，那就会找不到这些对应的页面，显示404。
```yaml
spring.thymeleaf.prefix=classpath:/static/
spring.thymeleaf.suffix=.html
spring.thymeleaf.encoding=UTF-8
# 关闭thymeleaf的缓存
spring.thymeleaf.cache=false
```
5.src/main/resources目录(根目录)
6.META/INF/resources目录
该目录在图片上没有,但是源码里面有, 网上也有说的. 以后用到再补充
![](https://i.loli.net/2021/01/16/fPc65S9xejModLO.png)
7.classpath路径解释
用maven构建 项目时，resources 目录就是默认的classpath，`classpath:mybatis/mapper/*.xml`和`classpath:/mybatis/mapper/*.xml`的区别，看起来只是一个/的区别, 但是不加/就是resources目录加上/ 就是整个模块的根目录。



####  总结
其实这些目录之中，最具有迷惑性的就是static目录和templates目录，static目录放js，css，images文件，templates放模版thymeleaf的动态html页面，没有使用thymeleaf或者其他的模板引擎时候，static下面的index.html会被解析为首页，但是如果使用了模版引擎，还是都在templates目录下定义动态模版或者是inedx.html，所有的html，而只是让static放js，css，images等资源文件，这样就可以明确职责，方便理解和调试。
https://blog.csdn.net/weixin_44757863/article/details/109394145，https://blog.csdn.net/zzzgd_666/article/details/88686970