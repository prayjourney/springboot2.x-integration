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
@ApiModel(value = "Parents对象", description = "")
public class Parents implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父母id")
    @TableId(value = "parentsId", type = IdType.AUTO)
    private Integer parentsId;

    @TableField("fatherName")
    private String fatherName;

    @TableField("motherName")
    private String motherName;


}
