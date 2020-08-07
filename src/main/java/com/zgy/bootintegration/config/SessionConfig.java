package com.zgy.bootintegration.config;

import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 * @Author: renjiaxin
 * @Despcription: Spring Session的配置， 使用springboot-session处理，单位：秒，
 *                RedisFlushMode有两个参数：ON_SAVE（表示在response commit前刷新缓存），IMMEDIATE（表示只要有更新，就刷新缓存）
 * @Date: Created in 2020/8/7 0:14
 * @Modified by:
 * @Url: https://blog.csdn.net/ljk126wy/article/details/93971421, https://www.cnblogs.com/yingsong/p/9838198.html
 */
// 配置的两个类：EnableRedisHttpSession 和 EnableJdbcHttpSession, 里面的一些配置情况可以看一下，表名，命名空间这些
// @EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800, redisFlushMode = RedisFlushMode.ON_SAVE,
//         redisNamespace = "springboot2.x-integration")
// @EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 1800, tableName = "spring_session")
public class SessionConfig {

}
