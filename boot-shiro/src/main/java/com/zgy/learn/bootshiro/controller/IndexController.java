package com.zgy.learn.bootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author renjiaxin
 * @Date 2020/11/5
 * @Description
 */
@Controller
public class IndexController {
    @RequestMapping({"", "/", "home", "index"})
    public String index(Model model) {
        model.addAttribute("message", "hello nihao a !");
        return "index";
    }
}
