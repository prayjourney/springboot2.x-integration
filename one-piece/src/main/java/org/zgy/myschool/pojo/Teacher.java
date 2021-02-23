package org.zgy.myschool.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: zgy
 * @since: 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Teacher对象", description = "")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "teId", type = IdType.AUTO)
    private Integer teId;

    @ApiModelProperty(value = "姓名")
    @TableField("teName")
    private String teName;

    @ApiModelProperty(value = "年龄")
    @TableField("teAge")
    private Integer teAge;

    @ApiModelProperty(value = "手机")
    @TableField("tePhoneNo")
    private String tePhoneNo;

    @ApiModelProperty(value = "性别")
    @TableField("teGender")
    private String teGender;

    @ApiModelProperty(value = "教授班级")
    @TableField("teClass")
    private String teClass;

    @ApiModelProperty(value = "教授科目")
    @TableField("teCourse")
    private String teCourse;

    @ApiModelProperty(value = "工作年限")
    @TableField("teWorkTime")
    private Integer teWorkTime;


}
