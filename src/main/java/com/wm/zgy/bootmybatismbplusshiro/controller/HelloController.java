package com.wm.zgy.bootmybatismbplusshiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author renjiaxin
 * @Date 2020/5/15
 * @Description
 */
@RestController
public class HelloController {
    @ResponseBody
    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
