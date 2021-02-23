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
@ApiModel(value = "Student对象", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "stId", type = IdType.AUTO)
    private Integer stId;

    @ApiModelProperty(value = "姓名")
    @TableField("stName")
    private String stName;

    @ApiModelProperty(value = "年龄")
    @TableField("stAge")
    private Integer stAge;

    @ApiModelProperty(value = "手机")
    @TableField("stPhoneNo")
    private String stPhoneNo;

    @ApiModelProperty(value = "性别")
    @TableField("stGender")
    private String stGender;

    @ApiModelProperty(value = "班级")
    @TableField("stClass")
    private String stClass;

    @ApiModelProperty(value = "家庭住址")
    @TableField("stHome")
    private String stHome;

    @ApiModelProperty(value = "父母id")
    @TableField("stParentsId")
    private Integer stParentsId;


}
