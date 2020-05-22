package com.wm.zgy.bootmybatismbplusshiroesquartz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author renjiaxin
 * @Date 2020/5/22
 * @Description
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

