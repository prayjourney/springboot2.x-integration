package com.zgy.learn.richtextwangeditor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: z.g.y
 * @date: 2020/11/23
 * @description:
 */
@Controller
@RequestMapping("wangeditor")
public class WangEditorController {

    @RequestMapping(value = {"", "/", "index"})
    public String index() {
        return "index";
    }
}
