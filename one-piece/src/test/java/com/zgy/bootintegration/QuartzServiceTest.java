package com.zgy.bootintegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.bootintegration.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: z.g.y
 * @date: 2020/5/22
 * @description: 测试定时任务
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuartzServiceTest {

    @Autowired
    @Qualifier("quartzService")
    QuartzService service;

    @Test
    public void testPrintTimeEveryMinutes() {
        service.printTimeEveryMinutes();
        log.info("print time ok" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    @Test
    public void testInsertOneStudent() throws JsonProcessingException {
        service.insertOneStudent();
        log.info("insert a student ok" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }
}
