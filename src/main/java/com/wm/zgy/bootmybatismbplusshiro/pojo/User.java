package com.wm.zgy.bootmybatismbplusshiro.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/17 21:41
 * @Modified by:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // 主键自增策略，有uuid, 自增id, 雪花算法， redis, zookeeper
//    @TableId(type = IdType.AUTO)
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
