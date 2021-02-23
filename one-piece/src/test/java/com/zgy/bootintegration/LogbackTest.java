package com.zgy.bootintegration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: z.g.y
 * @date: 2020/5/22
 * @description:
 */
@Slf4j
public class LogbackTest {

    public static void main(String[] args) {
        log.trace("logback的--trace日志--输出了");
        log.debug("logback的--debug日志--输出了");
        log.info("logback的--info日志--输出了");
        log.warn("logback的--warn日志--输出了");
        log.error("logback的--error日志--输出了");
    }

}

