package com.zgy.learn.bootshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.bootshiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author: zuiguangyin
 * @date: 2020/11/5
 * @description:
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from `user` where name = #{name}")
    User queryUserByName(String name);
}
