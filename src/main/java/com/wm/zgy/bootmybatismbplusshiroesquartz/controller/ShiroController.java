package com.wm.zgy.bootmybatismbplusshiroesquartz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/6/14 22:50
 * @Modified by:
 */
@RequestMapping("/shiro")
@Controller
@Slf4j
public class ShiroController {

    @RequestMapping(value = {"/index", "/test"})
    @ResponseBody
    public String shiroIndex() {
        return "hello shiro";
    }

    @RequestMapping("addmsg")
    public String add() {
        return "addmsg";
    }

    @RequestMapping("getmsg")
    public String getMsg() {
        return "getmsg";
    }

    @GetMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginCheck(String user, String password) {
        if (null == user || null == password) {
            return "404";
        } else if ("123456".equals(password)) {
            return "index";
        } else {
            return "login";
        }
    }

}
