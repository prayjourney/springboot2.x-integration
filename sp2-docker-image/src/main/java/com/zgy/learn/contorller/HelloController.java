package com.zgy.learn.contorller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgy
 * @date 2021/7/13
 */
@RestController
public class HelloController {
    @Value("${hello.msg}")
    private String msg;
    @Value("${slogan}")
    private String slogan;

    @RequestMapping("/hello")
    public String hello() {
        return msg;
    }

    @RequestMapping("/slogan")
    public String slogan() {
        return slogan;
    }
}
