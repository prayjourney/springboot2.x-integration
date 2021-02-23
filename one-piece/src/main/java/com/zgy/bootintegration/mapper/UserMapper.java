package com.zgy.bootintegration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.bootintegration.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/5/17 21:50
 * @modified:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectUserById(Integer id);

    User selectUserByName(String name);
}
