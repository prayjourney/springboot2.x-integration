package com.wm.zgy.bootmybatismbplusshiro;

import com.wm.zgy.bootmybatismbplusshiro.mapper.UserMapper;
import com.wm.zgy.bootmybatismbplusshiro.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootMybatisMbplusShiroApplicationTests {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}
