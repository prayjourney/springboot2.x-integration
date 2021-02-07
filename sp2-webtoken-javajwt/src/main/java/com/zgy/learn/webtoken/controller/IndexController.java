package com.zgy.learn.webtoken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-01-16
 * @modified:
 */
@Controller
public class IndexController {
    /**
     * index页面
     *
     * @return
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
