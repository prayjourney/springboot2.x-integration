package com.zgy.learn.springsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: pray-journey.io
 * @date: 2021/2/22
 */
@Controller
public class SceneryController {

    // @Secured("ROLE_vip")专门在方法或者类上面, 判断角色信息, 然后以"ROLE_"开头, hasRole方法不用写ROLE_, hasRole('vip')
    @Secured("ROLE_vip")
    @RequestMapping(value = "/scenery")
    public String scenery() {
        return "scenery";
    }

    // 在控制器方法上添加@PreAuthorize，参数可以是任何access()支持的表达式, 角色和权限都可以
    // @PreAuthorize("hasRole('vip')")
    // @PreAuthorize("hasRole('ROLE_vip')") // 这个也是可以的, 但是在配置的方式之中不可以这样操作
    @PreAuthorize("hasAuthority('user')")
    @RequestMapping(value = "/wall")
    public String wall(){
        return "wall";
    }
}
