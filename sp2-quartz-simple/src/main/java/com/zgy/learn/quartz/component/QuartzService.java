package com.zgy.learn.quartz.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author renjiaxin
 * @date 2021/4/19
 */
@Slf4j
@Component
public class QuartzService {
    @Scheduled(cron = "0/5 * * * * ? ")
    public void task() {
        log.error("hello : " + LocalDateTime.now());
    }
}
