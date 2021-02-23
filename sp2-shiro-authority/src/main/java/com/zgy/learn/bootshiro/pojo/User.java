package com.zgy.learn.bootshiro.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author: zuiguangyin
 * @date: 2020/11/5
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    /**
     * salt盐值
     */
    private String salt;

    /**
     * 权限
     */
    private String perms;
    /**
     * 角色
     */
    private String role;
}
