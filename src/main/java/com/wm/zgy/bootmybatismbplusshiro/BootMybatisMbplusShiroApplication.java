package com.wm.zgy.bootmybatismbplusshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wm.zgy.bootmybatismbplusshiro.mapper") // 扫描mapper文件夹
public class BootMybatisMbplusShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisMbplusShiroApplication.class, args);
    }

}
