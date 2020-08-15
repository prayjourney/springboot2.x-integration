package com.zgy.learn.bootvue.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 8/15/2020 11:10 PM
 * @Modified by:
 */
@Data
@Builder
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
}
