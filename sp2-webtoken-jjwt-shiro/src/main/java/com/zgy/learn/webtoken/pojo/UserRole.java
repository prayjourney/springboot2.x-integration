package com.zgy.learn.webtoken.pojo;

import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author makejava
 * @since 2021-02-01 00:58:42
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = 757615714314527453L;
    /**
     * OP用户ID
     */
    private Integer userId;
    /**
     * 角色ID
     */
    private Integer roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}