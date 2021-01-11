package com.zgy.learn.bootshiro.service;

import com.zgy.learn.bootshiro.pojo.User;

/**
 * @Author zuiguangying
 * @Date 2020/11/5
 * @Description
 */
public interface UserService {
    User queryUserByName(String name);
}
