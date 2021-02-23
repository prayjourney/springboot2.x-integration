package com.zgy.bootintegration.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.bootintegration.mapper.KidMapper;
import com.zgy.bootintegration.pojo.Kid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: z.g.y
 * @description: kidService
 * @date: Created in 2020/8/9 12:53
 * @modified:
 */
@Service
public class KidService {
    @Autowired
    KidMapper kidMapper;

    public Kid findKidById(Integer kidId) {
        return kidMapper.selectById(kidId);
    }

    public Kid findKidByName(String kidName) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", kidName);
        return kidMapper.selectOne(queryWrapper);
    }
}

