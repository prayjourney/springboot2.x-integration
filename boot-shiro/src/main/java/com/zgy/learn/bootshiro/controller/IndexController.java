package com.zgy.learn.bootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zuiguangying
 * @Date 2020/11/5
 * @Description
 */
@Controller
public class IndexController {
    @RequestMapping({"", "/", "home", "index"})
    public String index(Model model) {
        model.addAttribute("message", "这是来自后端的测试消息!");
        return "index";
    }
}
