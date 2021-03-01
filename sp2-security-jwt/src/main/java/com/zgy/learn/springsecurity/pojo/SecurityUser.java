package com.zgy.learn.springsecurity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: pray-journey.io
 * @date: 2021/2/24
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements Serializable {
    private static final long serialVersionUID = -1L;

    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private String password;
    // 使用Spring Security的时候在用户的实体之中不需要salt了, 生成的密码之中自包含
    // private String salt;
    private String roles;
    private String authorities;
    private Date createTime;
    private Date updateTime;
    // 账户可用
    private boolean enabled;
    // 账户没有过期
    private boolean accountNonExpired;
    // 凭证(密码)没有失效
    private boolean credentialsNonExpired;
    // 账户没有被锁定
    private boolean accountNonLocked;

}
