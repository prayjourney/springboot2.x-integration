package com.zgy.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-24
 * @modified:
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"", "/", "/index", "/home"})
    public String index() {
        return "index";
    }
}
