package com.zgy.learn.timeformat.hutool;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zgy
 * @date 2021/7/28
 */
public class CatDateTimeFormat {
    public static void main(String[] args) {
        Date now = new Date();
        LocalDateTime datetime = LocalDateTime.of(2011, 1, 12, 4, 15, 1);

        System.out.println("没有格式化的Date: " + now);
        System.out.println("没有格式化的LocalDateTime: " + datetime);

        MyCat mycat = new MyCat();
        mycat.setId(1).setName("小黄")
                .setBirthday(datetime)
                .setToday(now);
        System.out.println(mycat);
    }
}
