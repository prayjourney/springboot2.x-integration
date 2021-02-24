package com.zgy.learn.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 先要开启注解的使用
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityMybatisplusApplication.class, args);
    }

}
