package com.zgy.learn.token.controller;

import com.zgy.learn.token.pojo.Role;
import com.zgy.learn.token.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Role表控制层
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:23
 */
@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Role selectOne(Integer id) {
        return this.roleService.queryById(id);
    }

    /**
     * 创建角色
     *
     * @param name
     * @param displayName
     * @return
     */
    @PostMapping("createRole")
    public Role createRole(String name, String displayName) {
        Date now = new Date();
        Role role = new Role();
        role.setName(name).setDisplayName(displayName).setCreateTime(now).setUpdateTime(now);
        return this.roleService.insert(role);
    }

}