package com.zgy.learn.bootshiro.controller;

import com.zgy.learn.bootshiro.pojo.User;
import com.zgy.learn.bootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author renjiaxin
 * @Date 2020/11/6
 * @Description
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("querybyname")
    public User queryUserByName(String name) {
        return userService.queryUserByName(name);
    }
}
