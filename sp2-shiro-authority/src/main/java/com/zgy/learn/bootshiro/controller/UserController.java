package com.zgy.learn.bootshiro.controller;

import com.zgy.learn.bootshiro.pojo.User;
import com.zgy.learn.bootshiro.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author: z.g.y
 * @date: 2020/11/6
 * @description:
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("querybyname")
    public User queryUserByName(String name) {
        return userService.queryUserByName(name);
    }

    @PostMapping("create")
    @ResponseBody
    public String create(String name, String password) {
        if (null == name || null == password) {
            return "not null!";
        }

        User check = userService.queryUserByName(name);
        if (null != check) {
            return "already exist!";
        }

        String salt = UUID.randomUUID().toString();
        String algorithmName = "SHA-256";
        Integer hashNumber = 1024;
        String encodedPassword = new SimpleHash(algorithmName, password, salt, hashNumber).toString();
        User user = new User().setName(name)
                .setPassword(encodedPassword).setSalt(salt).setRole("student").setPerms("user:update");
        Integer res = userService.saveUser(user);
        if (1 == res) {
            return "success";
        } else {
            return "fail";
        }
    }
}
