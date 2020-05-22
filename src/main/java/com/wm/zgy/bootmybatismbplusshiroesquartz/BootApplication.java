package com.wm.zgy.bootmybatismbplusshiroesquartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.wm.zgy.bootmybatismbplusshiroesquartz.mapper") // 扫描mapper文件夹
@EnableScheduling // 开启定时任务
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
