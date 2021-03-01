package com.zgy.learn.sp2swaggerknife4j.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: z.g.y
 * @date: 2021/3/1
 */
@ApiModel(value = "教室对象")
@Data
@Accessors(chain = true)
public class Teacher {
    @ApiModelProperty(value = "教师id", required = true, example = "1")
    private int id;
    @ApiModelProperty(value = "教师姓名", required = true, example = "李花花")
    private String name;
    @ApiModelProperty(value = "教师年龄", required = true, example = "12")
    private int age;
    @ApiModelProperty(value = "性别", required = true, example = "false")
    private boolean gender;
    @ApiModelProperty(value = "教授科目", required = true, example = "自然")
    private String subject;
    @ApiModelProperty(value = "居住地址", required = true, example = "广州市荔湾区春光里22号")
    private String address;
    @ApiModelProperty(value = "电话号码", required = true, example = "18989898989")
    private String phone;
}
