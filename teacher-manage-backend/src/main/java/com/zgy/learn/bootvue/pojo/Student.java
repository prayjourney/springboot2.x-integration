package com.zgy.learn.bootvue.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 8/15/2020 11:10 PM
 * @Modified by:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
}
