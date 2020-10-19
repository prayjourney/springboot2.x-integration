package com.zgy.bootintegration.service;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/6/15 8:34
 * @Modified by:
 */

import com.zgy.bootintegration.mapper.UserMapper;
import com.zgy.bootintegration.pojo.User;
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
