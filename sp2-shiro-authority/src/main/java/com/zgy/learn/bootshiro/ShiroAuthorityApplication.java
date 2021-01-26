package com.zgy.learn.bootshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShiroAuthorityApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        try {
            SpringApplication.run(ShiroAuthorityApplication.class, args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
