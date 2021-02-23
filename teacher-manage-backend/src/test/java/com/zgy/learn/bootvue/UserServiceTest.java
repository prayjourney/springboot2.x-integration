package com.zgy.learn.bootvue;

import com.zgy.learn.bootvue.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/8/5 1:28
 * @modified:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService service;

    @Test
    public void testFindAll() {
        service.findAll().forEach(user -> System.out.println("user: " + user));
    }

    @Test
    public void testSelectByUserNameOrPhoneCode01() {
        System.out.println(service.selectByUserNameOrPhoneCode("天海翼", null));
    }

    @Test
    public void testSelectByUserNameOrPhoneCode02() {
        System.out.println(service.selectByUserNameOrPhoneCode(null, "17788991234"));
    }

    @Test
    public void testSelectByUserNameOrPhoneCode03() {
        System.out.println("service.selectByUserNameOrPhoneCode(\"小泽百惠\", \"18823894589\") = " + service.selectByUserNameOrPhoneCode("小泽百惠", "18823894589"));
    }

    @Test
    public void testSelectByUserNameOrPhoneCode04() {
        System.out.println("service.selectByUserNameOrPhoneCode(null, null) = " + service.selectByUserNameOrPhoneCode(null, null));
    }

    @Test
    public void testSelectByUserNameOrPhoneCode05() {
        System.out.println("service.selectByUserNameOrPhoneCode(\"\", \"\") = " + service.selectByUserNameOrPhoneCode("", ""));
    }
}
