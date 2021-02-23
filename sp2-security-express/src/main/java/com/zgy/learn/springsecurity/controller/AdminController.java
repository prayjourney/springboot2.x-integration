package com.zgy.learn.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-02-21
 * @modified :
 */
@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}
