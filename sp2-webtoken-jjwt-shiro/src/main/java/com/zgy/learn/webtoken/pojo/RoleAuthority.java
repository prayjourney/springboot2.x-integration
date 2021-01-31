package com.zgy.learn.webtoken.pojo;

import java.io.Serializable;

/**
 * (RoleAuthority)实体类
 *
 * @author makejava
 * @since 2021-02-01 00:58:22
 */
public class RoleAuthority implements Serializable {
    private static final long serialVersionUID = 615391973375882091L;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 权限ID
     */
    private Integer authorityId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

}