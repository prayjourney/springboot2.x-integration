package com.zgy.learn.token.controller;

import com.zgy.learn.token.pojo.Authority;
import com.zgy.learn.token.service.AuthorityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Authority表控制层
 *
 * @author: z.g.y
 * @since: 2021-02-01 00:55:24
 */
@RestController
@RequestMapping("authority")
public class AuthorityController {
    /**
     * 服务对象
     */
    @Resource
    private AuthorityService authorityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Authority selectOne(Integer id) {
        return this.authorityService.queryById(id);
    }

    /**
     * 创建权限
     *
     * @param name
     * @param displayName
     * @return
     */
    @PostMapping("createAuthority")
    public Authority createRole(String name, String displayName) {
        Date now = new Date();
        Authority authority = new Authority();
        authority.setName(name).setDisplayName(displayName).setCreateTime(now).setUpdateTime(now);
        return this.authorityService.insert(authority);
    }
}