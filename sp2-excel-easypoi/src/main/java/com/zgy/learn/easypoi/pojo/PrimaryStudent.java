package com.zgy.learn.easypoi.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/6/28
 * @modified:
 */
@Data
public class PrimaryStudent {
    @Excel(name = "id")
    private Integer id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "邮件", width = 20)
    private String email;
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "性别", replace = {"男_true", "女_false"})
    private Boolean sex;
    @Excel(name = "出生日期")
    private String bronDate;
    @Excel(name = "头像", type = 2, height = 20)
    private String headImage;

}
