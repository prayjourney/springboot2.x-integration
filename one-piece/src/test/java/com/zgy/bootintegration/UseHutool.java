package com.zgy.bootintegration;

import cn.hutool.core.convert.impl.DateConverter;
import cn.hutool.core.convert.impl.StringConverter;
import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: z.g.y
 * @date: 2020/8/17
 * @description:
 */
public class UseHutool {
    public static void main(String[] args) {
        String date1 = "2020-9-18";
        String date2 = "2020-09-18";
        String date3 = "2020/9/18";

        // String转换为Date
        DateConverter dateConverter01 = new DateConverter(Date.class);
        System.out.println(dateConverter01.convert(date1, new Date()));
        System.out.println(dateConverter01.convert(date2, new Date()));
        System.out.println(dateConverter01.convert(date3, new Date()));
        System.out.println(LocalDateTimeUtil.of(new Date()));

        // Date转成String
        LocalDateTime now = LocalDateTime.now();
        StringConverter stringConverter = new StringConverter();
        System.out.println(stringConverter.convert(now, ""));

    }
}
