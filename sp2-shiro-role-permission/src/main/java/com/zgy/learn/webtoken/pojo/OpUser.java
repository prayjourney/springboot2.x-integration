package com.zgy.learn.webtoken.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * OpUser实体类
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:43
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OpUser implements Serializable {
    private static final long serialVersionUID = 914918679313307010L;
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * Email
     */
    private String email;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 账号失效
     */
    private Object accountDisabled;
    /**
     * 账号过期
     */
    private Object accountExpired;
    /**
     * 账号冻结
     */
    private Object accountLocked;
    /**
     * 密码过期
     */
    private Object credentialsExpired;
    /**
     * 建立时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}