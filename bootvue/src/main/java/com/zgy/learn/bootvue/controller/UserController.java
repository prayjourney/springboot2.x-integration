package com.zgy.learn.bootvue.controller;

import com.zgy.learn.bootvue.pojo.User;
import com.zgy.learn.bootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/8/5 1:25
 * @Modified by:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @CrossOrigin // 跨域
    @ResponseBody
    @RequestMapping("findall")
    public List<User> findAll() {
        return service.findAll();
    }
}
