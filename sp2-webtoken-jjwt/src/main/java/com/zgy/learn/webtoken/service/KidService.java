package com.zgy.learn.webtoken.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.webtoken.mapper.KidMapper;
import com.zgy.learn.webtoken.pojo.Kid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zgy
 * @date: 2021/1/13
 * @description:
 */
@Service
public class KidService {
    @Autowired
    KidMapper kidMapper;

    public Kid findKidById(Integer kidId) {
        return kidMapper.selectById(kidId);
    }

    public Kid findKidByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", name);
        return kidMapper.selectOne(queryWrapper);
    }
}