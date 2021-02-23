package com.zgy.learn.config;

import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-24
 * @modified:
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800, flushMode = FlushMode.ON_SAVE,
        redisNamespace = "sp2-session-redis")
public class SessionConfig {

}
