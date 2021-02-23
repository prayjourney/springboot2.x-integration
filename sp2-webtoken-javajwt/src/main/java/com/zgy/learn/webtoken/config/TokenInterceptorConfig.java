package com.zgy.learn.webtoken.config;

import com.zgy.learn.webtoken.handler.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: zgy
 * @date: 2021/1/13
 * @description: token拦截器配置
 */
@Configuration
public class TokenInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    TokenInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
