package com.zgy.learn.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class QuartzSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzSimpleApplication.class, args);
    }

}
