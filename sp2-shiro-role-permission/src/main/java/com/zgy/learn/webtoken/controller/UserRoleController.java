package com.zgy.learn.webtoken.controller;

import com.zgy.learn.webtoken.pojo.UserRole;
import com.zgy.learn.webtoken.service.UserRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * UserRole表控制层
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:43
 */
@RestController
@RequestMapping("userRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserRole selectOne(Integer id) {
        return this.userRoleService.queryById(id);
    }


    /**
     * 给用户赋予角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    @PostMapping("createUserRole")
    public UserRole createUserRole(Integer userId, Integer roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId).setRoleId(roleId);
        return userRoleService.insert(userRole);
    }

}