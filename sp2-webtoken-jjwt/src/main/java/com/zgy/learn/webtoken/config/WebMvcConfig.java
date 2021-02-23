package com.zgy.learn.webtoken.config;

import com.zgy.learn.webtoken.handler.LoginTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-18
 * @modified:
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

    /**
     * 跨域支持，总的支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径, 设置允许跨域请求的域名, 设置header信息, 设置允许的方法
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
                .maxAge(3600);
    }
}
