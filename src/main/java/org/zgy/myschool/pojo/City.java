package org.zgy.myschool.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zgy
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="City对象", description="")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ctId", type = IdType.AUTO)
    private Integer ctId;

    @TableField("ctName")
    private String ctName;

    @TableField("ctProvince")
    private String ctProvince;


}
