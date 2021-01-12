package com.zgy.learn.bootshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootShiroApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(BootShiroApplication.class, args);
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

}
