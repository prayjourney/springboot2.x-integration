package com.zgy.learn.timeformat.annotation;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zgy
 * @date 2021/7/30
 * @description 小风扇
 */
@Data
@Accessors(chain = true)
public class LittleBlower {
    private String color;
    private String brand;
    /**
     * 生产日期
     */
    @MyDateFormat
    private Date productDateTime;
    /**
     * 购买日期
     */
    @MyDateFormat(format = "yyyy/MM/dd HH/mm/ss")
    private LocalDateTime buyDateTime;
}
