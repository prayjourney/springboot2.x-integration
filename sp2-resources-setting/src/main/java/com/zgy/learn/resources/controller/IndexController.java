package com.zgy.learn.resources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-16
 * @modified:
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
    @RequestMapping(value = "/toShow")
    public String toRedirect() {
        return "redirect:/show.html";
    }


    /**
     * 转发到某个请求, 其实普通的请求也就是请求转发的过程, /toHello和/hello访问的是同一个页面, 但是二者的url是不一样的
     *
     * @return
     */
    @RequestMapping(value = "/toHello")
    public String toForward() {
        return "forward:/hello";
    }
}
