package com.zgy.bootintegration.config;

import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 * @Author: renjiaxin
 * @Despcription: Spring Session的配置， 使用springboot-session处理，单位：秒，
 * RedisFlushMode有两个参数：ON_SAVE（表示在response commit前刷新缓存），IMMEDIATE（表示只要有更新，就刷新缓存）
 * @Date: Created in 2020/8/7 0:14
 * @Modified by:
 * @Url: https://blog.csdn.net/ljk126wy/article/details/93971421, https://www.cnblogs.com/yingsong/p/9838198.html
 */
@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 1800, tableName = "spring_session")
public class SessionConfig {

}
