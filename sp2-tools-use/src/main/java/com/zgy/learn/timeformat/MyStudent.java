package com.zgy.learn.timeformat;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zgy
 * @date 2021/7/28
 * @description Date和LocalDateTime格式化, 主要用于时间的展示, 具体怎么存储的, 没有什么问题
 */
@Data
@Accessors(chain = true)
public class MyStudent {
    private Integer id;
    private String name;
    private LocalDateTime birthday;
    private Date goToSchoolDateTime;

    @Override
    public String toString() {
        return "MyStudent(" +
                "id=" + id +
                ", name=" + name +
                ", birthday=" + TimeFormatUtil.localDateTimeFormatter.format(birthday) +
                ", goToSchoolDateTime=" + TimeFormatUtil.dateTimeFormatter.format(goToSchoolDateTime) +
                ')';
    }
}
