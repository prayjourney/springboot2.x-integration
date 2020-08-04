package com.zgy.learn.bootvue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.bootvue.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/8/5 1:18
 * @Modified by:
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    // 查询所有用户
    List<User> findAll();
}
