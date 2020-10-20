# multiple-datasource
multiple-datasource项目是springboot配置多个数据源的一个工程，有两个分支，分别是`druid+mybatisplus`和`package+mybatis`。

### druid+mybatisplus分支
`druid+mybatisplus`分支是采用了druid和mybatis-plus的方式实现的, 其实druid这块没有太大的问题，使用默认的HiKariCP也是可以的，
关键之中在于对于数据源的配置，这两部分是一致的，都如同下面的方式。不同之处在于对于数据源的使用，`druid+mybatisplus`由于使用了
`dynamic-datasource-spring-boot`方式， 配置pom.xml，添加`dynamic-datasource-spring-boot-starter`,然后再mapper接口之中，
或者service服务之中，甚至是方法之中，就可以定义具体要使用的数据源，仅仅就一个@DS('db1')，@DS('db2')就搞定了，而在`package+mybatis`分支
之中就需要专门的config类来完成，相比之下，后者不灵活，而且繁琐，所以使用这种方式更好。
```yaml
spring:
  datasource:
    dynamic:
      primary: db1 # 配置默认数据库
      datasource:
        db1: # 数据源1配置
          url: jdbc:mysql://localhost:3306/db1?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8
          username: root
          password: 123456
          driver-class-name: com.mysql.cj.jdbc.Driver
        db2: # 数据源2配置
          url: jdbc:mysql://localhost:3306/db2?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8
          username: root
          password: 123456
          driver-class-name: com.mysql.cj.jdbc.Driver
      durid:
        initial-size: 1
        max-active: 20
        min-idle: 1
        max-wait: 60000 # 单位是毫秒
        time-between-eviction-runs-millis: 60000 # 这个下面的不配置也是可以的, 不配置就不用去写druid的config, 也没有对应监控功能
        min-evictable-idle-time-millis: 300000 # 这个下面的不配置也是可以的, 不配置就不用去写druid的config, 也没有对应监控功能
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        test-on-borrow: false
        test-on-return: false
        pool-prepared-statements: true
        filters: stat,wall # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        maxPoolPreparedStatementPerConnectionSize: 20
        seGlobalDataSourceStat: true
        connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
 
```

### package+mybatis分支
`package+mybatis`分支的配置在数据源方面没什么差别，如下，上面的后半部分其实主要就是druid的配置，这个问题不是太大。主要不同的地方就是两个配置，
配置的java代码如下：
```yaml
spring:
  datasource:
    db1: # 数据源1
      jdbc-url: jdbc:mysql://localhost:3306/db1?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8
      username: root
      password: 123456
      driver-class-name: com.mysql.cj.jdbc.Driver
    db2: # 数据源2
      jdbc-url: jdbc:mysql://localhost:3306/db2?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8
      username: root
      password: 123456
      driver-class-name: com.mysql.cj.jdbc.Driver
```
下面的代码之中，最关键的就是mapper.xml位置的设置，如果设置错误，就无法正确使用。另外在多个数据源通过不同的包package配置的时候，就需要额外注意
namespace的问题，这个也是可能导致错误的一个很大的诱导因素。其他的就没有太大的问题了。
```java
@Configuration
@MapperScan(basePackages = {"com.zgy.multipledatasource.mapper.db1"}, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DB1DataSourceConfig {

    // @Primary 表示这个数据源是默认数据源, 这个注解必须要加，因为不加的话spring将分不清楚那个为主数据源（默认数据源）
    @Primary
    @Bean("db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1") //读取application.yml中的配置参数映射成为一个对象
    public DataSource getDb1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("db1SqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 最好在此处设置, 在application.yml之中, 设置不设置都可以, 但是此处必须设置, 否则只有@Primary的可以使用
        // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/db1/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean("db1SqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
```
`package+mybatis`分支采用的是mybatis+包package的方式实现多数据源的切换，其实在这个分支之中，使用mybatis-plus+包package的方式也是可以的，
二者的差别就是mybatis和mybatis-plus的差别，由于mybatis-plus集成了BaseMapper，所以我们可以写很少的方法，而对于Mapper.xml和Mapper接口本身的
位置和配置的问题，其实没有任何差别。
使用mybatis-plus的starter的时候，不需要引入mybatis的starter，使用mybatis-plus的starter之中已经引入了mybatis的jar包，我们不需要额外引入。

##### 参考：
[springboot-整合多数据源配置](https://www.cnblogs.com/aizen-sousuke/p/11756279.html), 
[手把手教你用springboot配置多数据源](https://blog.csdn.net/qq_41076797/article/details/82889770), 
[dynamic-datasource-spring-boot-starter 多数据源配置](https://www.jianshu.com/p/0b408e4e14a4), 
https://github.com/baomidou/dynamic-datasource-spring-boot-starter, 
https://github.com/CtrlZ1/MultipleDataSource, https://www.jianshu.com/p/099c0850ba16, https://blog.csdn.net/qq_38058332/article/details/84325009, 
https://blog.csdn.net/z357904947/article/details/89157281
