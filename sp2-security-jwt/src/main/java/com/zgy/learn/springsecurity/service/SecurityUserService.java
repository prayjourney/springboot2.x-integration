package com.zgy.learn.springsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.springsecurity.mapper.SecurityUserMapper;
import com.zgy.learn.springsecurity.pojo.SecurityUser;
import org.springframework.stereotype.Service;

/**
 * @author: pray-journey.io
 * @date: 2021/2/24
 */
@Service
public class SecurityUserService {
    private SecurityUserMapper securityUserMapper;

    public SecurityUserService(SecurityUserMapper securityUserMapper) {
        this.securityUserMapper = securityUserMapper;
    }

    public SecurityUser getById(int id) {
        return securityUserMapper.selectById(id);
    }

    public SecurityUser getByName(String username) {
        QueryWrapper<SecurityUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return securityUserMapper.selectOne(queryWrapper);
    }

    public int insertUser(SecurityUser securityUser) {
        int result = securityUserMapper.insert(securityUser);
        return result;
    }
}
