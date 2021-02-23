package com.zgy.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: z.g.y
 * @date: 2021/1/27
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FileUploadInterceptor fileUploadInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截/file/**请求
        registry.addInterceptor(fileUploadInterceptor).addPathPatterns("/file/**")
                .excludePathPatterns("/public/**", "/assets/**", "/images/**", "/js/**", "/style/**", "/captcha/**",
                        "/error", "/favicon.ico");
    }

}

