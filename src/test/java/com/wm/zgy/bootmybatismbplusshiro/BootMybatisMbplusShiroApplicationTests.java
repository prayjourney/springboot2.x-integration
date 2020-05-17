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

    /**
     * 所有的方法，至此都来自于BaseMapper父类，基本的CRUD都已经OKAY了，有特殊的，我们就可以去自己写了
     */
    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(3);
        user.setEmail("2246890834578@qq.com");
        user.setName("张三");
        userMapper.insert(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(2L);
        user.setName("张自忠");
        user.setEmail("helloworld@gmail.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
}
