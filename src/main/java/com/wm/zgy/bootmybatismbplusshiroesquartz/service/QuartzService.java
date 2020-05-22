package com.wm.zgy.bootmybatismbplusshiroesquartz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author renjiaxin
 * @Date 2020/5/22
 * @Description
 */
@Slf4j
@Component
public class QuartzService {
    private String timeFormatter = "yyyy-MM-dd HH:mm:ss";

    //每一分钟都打印一次时间
    @Scheduled(cron = "0 * * * * ?")
    public String printTimeEveryMinutes() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter)).toString();
        log.info("print now time : {}", nowTime);
        return nowTime;
    }
}
