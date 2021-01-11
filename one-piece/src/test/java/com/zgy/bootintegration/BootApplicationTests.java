package com.zgy.bootintegration;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgy.bootintegration.mapper.UserMapper;
import com.zgy.bootintegration.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTests {

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
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1262043073220792322L, 1262043073220792322L));
        Assert.assertEquals(0, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(3);
        user.setEmail("2246890834578@qq.com");
        user.setName("Kings !");
        userMapper.insert(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(3L);
        user.setName("zhanglingfu!");
        user.setEmail("helloworld@gmail.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    // 成功
    @Test
    public void testOptimsticLockOkay() {
        // 查询用户信息
        User user = userMapper.selectById(1L);
        // 修改用户信息
        user.setName("李四");
        user.setEmail("mg123@qq.com");
        // 执行更新操作
        userMapper.updateById(user);
    }


    // 失败
    @Test
    public void testOptimsticLockFail() {
        // 查询用户信息
        User user = userMapper.selectById(3L);
//        new Thread(() -> {
//            try {
//                // 修改用户信息
//                user.setName("李四2222");
//                user.setEmail("mg123@qq.com");
//                // 执行更新操作
//                TimeUnit.SECONDS.sleep(1);
//                userMapper.updateById(user);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "t1").start();
//        new Thread(() -> {
//            // 修改用户信息
//            user.setName("李四所方法");
//            user.setEmail("mg12====3@qq.com");
//            // 执行更新操作
//            userMapper.updateById(user);
//        }, "t2").start();

        user.setName("李四");
        user.setEmail("mg123@qq.com");

        // 模拟另外一个线程插队执行
        User user2 = userMapper.selectById(3L);
        user2.setName("李四111");
        user2.setEmail("mg123@qq.com");
        userMapper.updateById(user2);

        userMapper.updateById(user); //如果没有乐观锁，就会插队成功，就会覆盖，名字变成李四111.实际上没有被覆盖，修改后还是李四111
    }

    // 批量查询
    @Test
    public void testBatchSelect() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        users.forEach(System.out::println);
    }

    // 条件查询，封装在map里面，我们不用去拼接动态SQL了
    @Test
    public void testContionSelect() {
        HashMap<String, Object> map = new HashMap();
        map.put("name", "张三");
        map.put("age", 3);

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    // 分页查询
    @Test
    public void testPageSelect() {
        // param1:当前页，param2:页面大小
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println("一共有：" + page.getTotal() + " 条。");
    }

    // 删除操作
    @Test
    public void testDelete() {
        userMapper.deleteById(1262042182942023681L);
    }

    // 删除操作, 通过map的方式
    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Kings !");
        userMapper.deleteByMap(map);
    }

    // 逻辑删除
    @Test
    public void testDeleteById() {
        userMapper.deleteById(1262043073220792322L);
    }

    // 逻辑删除, 批量
    @Test
    public void testDeleteByBatchIds() {
        userMapper.deleteBatchIds(Arrays.asList(1262043073220792322L, 1262041503661940738L));
    }

    // Wrapper， 用于比较复杂的SQL
    @Test
    public void testWrapperQuery() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 和map类似， 支持链式编程，name不空， age大于18， version为1
        wrapper.isNotNull("name").ge("age", 18).eq("version", 1);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    // 模糊查询
    @Test
    public void testWrapperQueryLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 和map类似， 支持链式编程，name不空， age大于18， version为1
        wrapper.like("name", "张");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
