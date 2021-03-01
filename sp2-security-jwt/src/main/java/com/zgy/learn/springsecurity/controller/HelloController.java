package com.zgy.learn.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-02-21
 * @modified :
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        // return "redirect:/hello"; // 后端直接重定向页面
        return "hello";
    }

    @RequestMapping(value = "/tohello", method = RequestMethod.POST)
    public String toHello() {
        // 没有这个, 表示没有过来
        System.out.println("111111111111111");
        return "hello";
    }
}
