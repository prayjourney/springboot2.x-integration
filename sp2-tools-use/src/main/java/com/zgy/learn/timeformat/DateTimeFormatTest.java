package com.zgy.learn.timeformat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zgy
 * @date 2021/7/28
 */
public class DateTimeFormatTest {
    public static void main(String[] args) {
        Date now = new Date();
        LocalDateTime datetime = LocalDateTime.of(2011, 1, 12, 4, 15, 1);

        System.out.println("没有格式化的Date: " + now);
        System.out.println("没有格式化的LocalDateTime: " + datetime);

        MyStudent myStudent = new MyStudent();
        myStudent.setId(1).setName("张三")
                .setBirthday(datetime)
                .setGoToSchoolDateTime(now);
        System.out.println(myStudent);
    }
}
