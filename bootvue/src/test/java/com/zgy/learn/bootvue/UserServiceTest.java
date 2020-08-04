package com.zgy.learn.bootvue;

import com.zgy.learn.bootvue.pojo.User;
import com.zgy.learn.bootvue.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/8/5 1:28
 * @Modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService service;

    @Test
    public void testFindAll(){
        service.findAll().forEach(user -> System.out.println("user: " + user));
    }

}
