package com.zgy.learn.timeformat.annotation;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONObject;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author zgy
 * @date 2021/7/30
 */
public class LittleBlowerStatus {
    public static void main(String[] args) {

        LittleBlower littleBlower = new LittleBlower();
        littleBlower.setBrand("小虎牙").setColor("red").setBuyDateTime(LocalDateTime.now()).setProductDateTime(new Date());
        System.out.println(littleBlower);

        // 此处进行了信息的修改
        Map<String, Field> annotation = MyDateFormatProcessor.getAnnotation(littleBlower.getClass());
        String buyDateTimeFormat = annotation.get("buyDateTime").getAnnotation(MyDateFormat.class).format();
        String productDateTimeFormat = annotation.get("productDateTime").getAnnotation(MyDateFormat.class).format();
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("brand", littleBlower.getBrand());
        jsonObject.set("color", littleBlower.getColor());
        jsonObject.set("buyDateTime", LocalDateTimeUtil.format(littleBlower.getBuyDateTime(), buyDateTimeFormat));
        jsonObject.set("productDateTime", DateUtil.format(littleBlower.getProductDateTime(), productDateTimeFormat));

        System.out.println(jsonObject);
    }
}
