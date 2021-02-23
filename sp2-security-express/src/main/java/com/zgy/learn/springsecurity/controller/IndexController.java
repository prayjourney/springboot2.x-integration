package com.zgy.learn.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-02-21
 * @modified :
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index", "/home"})
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/toerror", method = RequestMethod.POST)
    public String toError() {
        return "error";
    }

}
