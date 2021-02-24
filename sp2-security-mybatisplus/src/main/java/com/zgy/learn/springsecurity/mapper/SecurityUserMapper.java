package com.zgy.learn.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.springsecurity.pojo.SecurityUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: pray-journey.io
 * @date: 2021/2/24
 */
@Mapper
@Repository
public interface SecurityUserMapper extends BaseMapper<SecurityUser> {
}
