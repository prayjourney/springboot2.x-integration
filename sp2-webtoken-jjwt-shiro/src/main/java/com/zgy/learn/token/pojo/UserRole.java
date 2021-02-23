package com.zgy.learn.token.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * UserRole实体类
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:23
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
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

}