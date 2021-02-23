package com.zgy.learn.bootvue.service;

import com.zgy.learn.bootvue.mapper.UserMapper;
import com.zgy.learn.bootvue.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/8/5 1:25
 * @modified:
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    UserMapper mapper;

    public List<User> findAll() {
        return mapper.findAll();
    }

    public Integer saveUser(User user) {
        return mapper.saveUser(user);
    }

    public Integer deleteUserById(Integer id) {
        return mapper.deleteUserById(id);
    }

    public User selectUserById(Integer id) {
        return mapper.selectUserById(id);
    }

    public Integer updateUserById(User user) {
        return mapper.updateUserById(user);
    }

    // 动态sql:https://www.jianshu.com/p/6b0227935d0f
    public List<User> selectByUserNameOrPhoneCode(String name, String phoneCode) {
        return mapper.selectByUserNameOrPhoneCode(name, phoneCode);
    }
}
