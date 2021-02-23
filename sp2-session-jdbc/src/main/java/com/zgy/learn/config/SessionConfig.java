package com.zgy.learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-24
 * @modified:
 * @url: https://blog.csdn.net/ljk126wy/article/details/93971421, https://www.cnblogs.com/yingsong/p/9838198.html
 */
@Configuration
@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 1800, tableName = "spring_session")
public class SessionConfig {

}
