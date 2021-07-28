package com.zgy.learn.timeformat.hutool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zgy
 * @date 2021/7/28
 * @description LocalDateTimeUtil 对LocalDateTime格式化, DateUtil对Date格式化
 */
@Data
@Accessors(chain = true)
public class MyCat {
    private Integer id;
    private String name;
    private LocalDateTime birthday;
    private Date today;

    @Override
    public String toString() {
        return "MyCat(" +
                "id=" + id +
                ", name=" + name +
                ", birthday=" + LocalDateTimeUtil.format(birthday, "yyyy-MM-dd HH:mm:ss") +
                ", today=" + DateUtil.format(getToday(), "yyyy-MM-dd HH:mm:ss") +
                ')';
    }
}
