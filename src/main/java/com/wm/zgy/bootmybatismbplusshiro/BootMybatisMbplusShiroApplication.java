package com.wm.zgy.bootmybatismbplusshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BootMybatisMbplusShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisMbplusShiroApplication.class, args);
    }

}
