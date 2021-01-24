package com.zgy.learn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM `user` WHERE `id` = #{id}")
    User selectUserById(Integer id);

    @Select("SELECT * FROM `user` WHERE `name` = #{name}")
    User selectUserByName(String name);
}
