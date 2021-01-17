package com.zgy.learn.webtoken.config;

import com.zgy.learn.webtoken.handler.LoginTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-01-18
 * @modified :
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    LoginTokenInterceptor loginTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，放过资源请求, 验证码, 错误页, 首页
        registry.addInterceptor(loginTokenInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/public/**", "/assets/**", "/images/**", "/js/**", "/css/**", "/captcha/**",
                        "/error", "/", "/index", "/home");
    }
}
