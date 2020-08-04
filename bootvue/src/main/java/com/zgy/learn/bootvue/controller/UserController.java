package com.zgy.learn.bootvue.controller;

import com.zgy.learn.bootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/8/5 1:25
 * @Modified by:
 */
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService service;
}
