package com.zgy.learn.bootvue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.bootvue.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/8/5 1:18
 * @modified:
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    // 查询所有用户
    List<User> findAll();

    Integer saveUser(User user);

    Integer deleteUserById(Integer id);

    User selectUserById(Integer id);

    Integer updateUserById(User user);

    // 多个条件，非对象的情况， 最好用@Param注解标注一下
    List<User> selectByUserNameOrPhoneCode(@Param("name") String name, @Param("phoneCode") String phoneCode);

    // 多个条件，非对象的情况， 最好用@Param注解标注一下
    List<User> selectByUserNameOrPhoneCode02(@Param("name") String name, @Param("phoneCode") String phoneCode);

}
