- [x] springboot整合富文本编辑器
- [x] springboot整合shiro
- springboot整合生成二维码
    - [x] zxing方式
    - [x] hutool方式
- springboot整合生成验证码
    - [x] hutool方式
    - kaptcha
        - [x] xml方式
        - [x] config方式
- [x] springboot普通文件上传下载
- [x] springboot大文件上传
- [x] springboot整合druid和mybatis-plus
    - 这个整合其实是很「无感」的, 基本就是DruidConfig+MybatisPlusConfig, 然后再到application.properties之中配置, 
    druid的配置是spring.datasource.druid与db配置的部分, mybatis-plus的配置就是mybatis-plus.mapper-locations等
    mybatis-plus.xxx的相关配置，这个具体的配置实在没有太多演示的需要，可以参看例如`sp2-webtoken-javajwt`模块。
- springboot整合spring-session
    - [x] jdbc方式
    - [x] redis方式
- springboot+mybatis整合多数据源
    - [x] package方式
    - [x] dynamic datasource方式
- springboot整合jwt
    - [x] java-jwt方式
    - [x] jjwt方式

<hr style='background-color:skyblue;height:1px;border:none;'/>

- [ ] springboot整合线程池

- [ ] springboot整合redis

- [ ] springboot整合mongodb

- [x] springboot整合swagger

- [x] springboot整合knife4j

- [x] springboot整合Quartz

- [ ] springboot整合ElasticSearch

- 数据分页
    - [x] mybatis + pagehelper
    - [ ] mybatisplus + PaginationInterceptor

- spring security
    - [x] springboot整合spring security
    - [x] spring security整合mybatis-plus
    - [x] spring security整合jwt(说明：**jwt的方式更加适合于前后端完全分离的状态, 传统方式还是用session**)

- [ ] springboot整合java mail

- springboot+docker
    - [x] springboot项目打包成docker镜像