package com.zgy.learn.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: pray-journey.io
 * @date: 2021/2/22
 */
@Controller
public class ThymeleafController {

    // 任意一个权限都可以
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    @RequestMapping("/thymeleaf")
    public String thymeleaf() {
        return "thymeleaf";
    }
}
