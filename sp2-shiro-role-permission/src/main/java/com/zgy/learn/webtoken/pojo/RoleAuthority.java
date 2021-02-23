package com.zgy.learn.webtoken.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * RoleAuthority实体类
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

}