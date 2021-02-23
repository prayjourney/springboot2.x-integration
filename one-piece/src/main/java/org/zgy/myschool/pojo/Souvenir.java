package org.zgy.myschool.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "Souvenir对象", description = "")
public class Souvenir implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "soId", type = IdType.AUTO)
    private Integer soId;

    @TableField("soName")
    private String soName;

    @TableField("ctId")
    private Integer ctId;


}
