package com.wm.zgy.bootmybatismbplusshiroesquartz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wm.zgy.bootmybatismbplusshiroesquartz.mapper.StudentMapper;
import com.wm.zgy.bootmybatismbplusshiroesquartz.pojo.Student;
import com.wm.zgy.bootmybatismbplusshiroesquartz.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @Author renjiaxin
 * @Date 2020/5/22
 * @Description
 */
@Slf4j
@Component
public class QuartzService {

    @Autowired(required = false)
    //@Qualifier("StudentMapper")
    private StudentMapper studentMapper;
    private String timeFormatter = "yyyy-MM-dd HH:mm:ss";

    //每一分钟都打印一次时间
    @Scheduled(cron = "0 * * * * ?")
    public String printTimeEveryMinutes() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter)).toString();
        log.info("print now time : {}", nowTime);
        return nowTime;
    }

    //从0分钟开始，每3分钟执行一次插入
    @Scheduled(cron = "0 0/3 * * * ? ")
    public String insertOneStudent() throws JsonProcessingException {
        List<String> names = Arrays.asList("张三", "lisi", "雨涵", "kiristina", "MG-HOTdog!");
        List<String> cities = Arrays.asList("四川省乐山市", "四川省成都市", "江苏省南京市", "广东省广州市", "北京市");
        Student student = Student.builder().cityId(1).classId(2).hobbyGroupId(1).stAge(22).stGender('男')
                .stHome(cities.get(new Random().nextInt(5)))
                .stName(names.get(new Random().nextInt(5)))
                .stId(Integer.valueOf(String.valueOf(UUID.randomUUID().getLeastSignificantBits()).substring(10, 15)))
                .build();
        studentMapper.insert(student);
        log.info("插入学生成功，时间是： "+ LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        return JSONUtil.getJsonFromObject(student);
    }
}
