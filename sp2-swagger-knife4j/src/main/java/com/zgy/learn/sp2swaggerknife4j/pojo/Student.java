package com.zgy.learn.sp2swaggerknife4j.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: z.g.y
 * @date: 2021/3/1
 */
// @ApiModel注解在POJO类上面, 描述对象
@ApiModel(value = "学生对象")
@Data
@Accessors(chain = true)
public class Student {
    // @ApiModelProperty字段描述, 描述, 是否必须, 例值
    @ApiModelProperty(value = "学生id", required = true, example = "1")
    private int id;
    @ApiModelProperty(value = "学生姓名", required = true, example = "张三")
    private String name;
    @ApiModelProperty(value = "学生年龄", required = true, example = "12")
    private int age;
    @ApiModelProperty(value = "性别", required = true, example = "false")
    private boolean gender;
    @ApiModelProperty(value = "年级", required = true, example = "五年级")
    private String grade;
    @ApiModelProperty(value = "教室", required = true, example = "二班")
    private String room;
    @ApiModelProperty(value = "居住地址", required = true, example = "北京市大哥胡同")
    private String address;
}
