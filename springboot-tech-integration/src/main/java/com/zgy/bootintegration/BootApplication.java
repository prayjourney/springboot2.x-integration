package com.zgy.bootintegration;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@MapperScan("com.zgy.bootintegration.mapper") // 扫描mapper文件夹
@EnableScheduling // 开启定时任务
@EnableAsync(proxyTargetClass = true) //aop的代理，使用cglib方式，默认是jdk方式
@ImportResource(locations = {"classpath:kaptcha.xml"})
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
        log.trace("logback的--trace日志--输出了");
        log.debug("logback的--debug日志--输出了");
        log.info("logback的--info日志--输出了");
        log.warn("logback的--warn日志--输出了");
        log.error("logback的--error日志--输出了");
    }

}
