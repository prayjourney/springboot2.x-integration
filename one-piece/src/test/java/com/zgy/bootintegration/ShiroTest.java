package com.zgy.bootintegration;

import com.zgy.bootintegration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/6/15 8:39
 * @modified:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ShiroTest {
    @Autowired
    private UserService service;

    @Test
    public void testGetUserById() {
        System.out.println(service.queryUserById(1));
    }

    @Test
    public void testGetUserByName() {
        System.out.println(service.queryUserByName("李四"));
    }
}
