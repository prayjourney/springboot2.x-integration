package com.zgy.learn.bootshiro.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author zuiguangyin
 * @Date 2020/11/5
 * @Description
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    /**
     * 权限
     */
    private String perms;
    /**
     * 角色
     */
    private String role;
}
