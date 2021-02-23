package com.zgy.learn.bootshiro.service;

import com.zgy.learn.bootshiro.mapper.UserMapper;
import com.zgy.learn.bootshiro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zuiguangyin
 * @date: 2020/11/5
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public Integer saveUser(User user){
        return userMapper.insert(user);
    }

}
