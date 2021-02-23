package com.zgy.learn.bootshiro.service;

import com.zgy.learn.bootshiro.pojo.User;

/**
 * @author: zuiguangying
 * @date: 2020/11/5
 * @description:
 */
public interface UserService {
    User queryUserByName(String name);

    Integer saveUser(User user);
}
