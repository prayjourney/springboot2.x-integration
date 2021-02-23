package com.zgy.learn.resources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-16
 * @modified:
 */
@Controller
public class HelloController {
    /**
     * hello功能的首页
     *
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}
