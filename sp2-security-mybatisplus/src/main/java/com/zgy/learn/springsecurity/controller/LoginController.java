package com.zgy.learn.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-03-27
 * @modified:
 */
@Controller
public class LoginController {

    @PostMapping("/mylogin")
    @ResponseBody
    public String mylogin(HttpServletRequest request, HttpServletResponse response) {
        return request.getParameter("usr");
    }
}
