package com.zgy.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: z.g.y
 * @date: 2021/1/28
 */
@Controller
public class IndexController {
    @GetMapping(value = {"", "/", "/index"})
    public String index() {
        return "index";
    }
}
