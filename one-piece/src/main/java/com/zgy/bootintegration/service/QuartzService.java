package com.zgy.bootintegration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.bootintegration.mapper.StudentMapper;
import com.zgy.bootintegration.pojo.Student;
import com.zgy.bootintegration.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: z.g.y
 * @date: 2020/5/22
 * @description:
 * @url: cron的介绍博客
 * https://www.cnblogs.com/zouhong/p/11332126.html
 * https://juejin.im/entry/5bb9bfd0f265da0af609c755
 * https://www.cnblogs.com/chrischennx/p/7652087.html
 * https://blog.csdn.net/asdfghzqlj/article/details/80586209
 * https://segmentfault.com/q/1010000015784016
 * https://segmentfault.com/a/1190000015253688
 * https://www.cnblogs.com/zouhong/p/11332126.html
 */
@Slf4j
@Component
public class QuartzService {

    @Autowired(required = false)
    //@Qualifier("StudentMapper")
    private StudentMapper studentMapper;
    private String timeFormatter = "yyyy-MM-dd HH:mm:ss";

    //每一分钟都打印一次时间
    //@Scheduled(cron = "0 * * * * ?")
    public String printTimeEveryMinutes() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter)).toString();
        log.info("print now time : {}", nowTime);
        return nowTime;
    }

    //从0分钟开始，每3分钟执行一次插入
    //@Scheduled(cron = "0 0/3 * * * ? ")
    public String insertOneStudent() throws JsonProcessingException {
        List<String> names = Arrays.asList("张三", "lisi", "雨涵", "kiristina", "MG-HOTdog!");
        List<String> cities = Arrays.asList("四川省乐山市", "四川省成都市", "江苏省南京市", "广东省广州市", "北京市");
        Student student = Student.builder().cityId(1).classId(2).hobbyGroupId(1).stAge(22).stGender('男')
                .stHome(cities.get(new Random().nextInt(5)))
                .stName(names.get(new Random().nextInt(5)))
                .stId(Integer.valueOf(String.valueOf(UUID.randomUUID().getLeastSignificantBits()).substring(10, 15)))
                .build();
        studentMapper.insert(student);
        log.info("插入学生成功，时间是： " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        return JacksonUtil.getJsonFromObject(student);
    }

    // 每一分钟执行一次, 但是一分钟执行不完
    // @Scheduled(cron = "0 0/1 0,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23 * * ? ")
    public String taskTimeMoreThanSettingMinutes() throws InterruptedException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter)).toString();
        log.info("每一分钟执行一次, 但是一分钟执行不完:::  + print now time : {}", nowTime);
        TimeUnit.MINUTES.sleep(2);
        log.info("MGGGGG==========  + print now time : {}", nowTime);
        return nowTime;
    }

    // 执行耗时68s, 并且执行完之后等待1分钟, 再去执行新的任务
    // 只能有一个的corn或者fixedDelay, fixedRate使用
    // @Scheduled(cron = "0/30 * 0,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23 * * ? " fixedDelayString = "60000")
    @Scheduled(fixedDelayString = "60000")
    public String taskTimeMoreThanSettingMinutes02() throws InterruptedException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter)).toString();
        log.info("执行了:::  + print now time : {}", nowTime);
        TimeUnit.SECONDS.sleep(68);
        log.info("执行执行...... + print now time : {}", nowTime);
        return nowTime;
    }
}
