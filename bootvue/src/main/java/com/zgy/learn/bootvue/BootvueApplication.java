package com.zgy.learn.bootvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zgy.learn.bootvue.mapper") // 扫描mapper文件夹
public class BootvueApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootvueApplication.class, args);
    }

}
