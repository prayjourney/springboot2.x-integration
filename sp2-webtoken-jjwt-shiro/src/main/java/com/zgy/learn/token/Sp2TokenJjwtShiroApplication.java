package com.zgy.learn.token;

import org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// SpringBoot加入Shiro导致AOP失效的坑: https://juejin.cn/post/6844904127525306382
@SpringBootApplication(exclude = {ShiroAnnotationProcessorAutoConfiguration.class})
public class Sp2TokenJjwtShiroApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Sp2TokenJjwtShiroApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
