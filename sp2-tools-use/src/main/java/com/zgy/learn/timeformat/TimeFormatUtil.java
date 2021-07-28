package com.zgy.learn.timeformat;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * @author zgy
 * @date 2021/7/28
 */
public class TimeFormatUtil {
    // Date格式化
    static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // LocalDateTime格式化
    static DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
