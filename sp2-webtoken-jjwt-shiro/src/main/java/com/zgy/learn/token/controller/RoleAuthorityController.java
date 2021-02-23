package com.zgy.learn.token.controller;

import com.zgy.learn.token.pojo.RoleAuthority;
import com.zgy.learn.token.service.RoleAuthorityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * RoleAuthority表控制层
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:58:23
 */
@RestController
@RequestMapping("roleAuthority")
public class RoleAuthorityController {
    /**
     * 服务对象
     */
    @Resource
    private RoleAuthorityService roleAuthorityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RoleAuthority selectOne(Integer id) {
        return this.roleAuthorityService.queryById(id);
    }

    /**
     * 给角色赋予权限
     *
     * @param roleId
     * @param authorityId
     * @return
     */
    @PostMapping("createRoleAuthority")
    public RoleAuthority createRoleAuthority(Integer roleId, Integer authorityId) {
        RoleAuthority roleAuthority = new RoleAuthority();
        roleAuthority.setRoleId(roleId).setAuthorityId(authorityId);
        return roleAuthorityService.insert(roleAuthority);
    }

}