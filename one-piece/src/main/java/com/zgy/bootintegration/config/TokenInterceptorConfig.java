package com.zgy.bootintegration.config;

import com.zgy.bootintegration.handler.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: z.g.y
 * @description: Token的拦截器配置
 * @date: Created in 2020/8/9 12:18
 * @modified:
 */
@Configuration
public class TokenInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    TokenInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
