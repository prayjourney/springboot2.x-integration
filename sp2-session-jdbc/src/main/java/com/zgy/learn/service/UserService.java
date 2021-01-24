package com.zgy.learn.service;

import com.zgy.learn.mapper.UserMapper;
import com.zgy.learn.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    public User queryUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    public User queryUserById(Integer id) {
        return userMapper.selectUserById(id);
    }
}
