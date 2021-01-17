package com.zgy.learn.resources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-01-16
 * @modified :
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"/", "/index", "/home"})
    public String index() {
        return "index";
    }


    /**
     * 重定向到静态资源的页面, 这个页面没有在thymeleaf动态模版之中, 所以只能重定向到这儿
     *
     * @return
     */
    @RequestMapping(value = "/show")
    public String toShow() {
        return "redirect:/show.html";
    }
}
