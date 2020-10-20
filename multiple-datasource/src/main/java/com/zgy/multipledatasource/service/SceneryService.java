package com.zgy.multipledatasource.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.multipledatasource.mapper.SceneryMapper;
import com.zgy.multipledatasource.pojo.Scenery;
import com.zgy.multipledatasource.pojo.Worker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author renjiaxin
 * @Date 2020/10/20
 * @Description
 */
@Service
@Slf4j
public class SceneryService {
    @Autowired
    private SceneryMapper sceneryMapper;

    public Scenery selectSceneryById(Integer id) {
        return sceneryMapper.selectById(id);
    }

    public int deleteSceneryById(Integer id) {
        return sceneryMapper.deleteById(id);
    }

    public String selectSceneryCountryById(Integer id) {
        return sceneryMapper.selectById(id).getCountry();
    }

    public String getSceneryNameById(Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        return sceneryMapper.selectOne(wrapper).getName();
    }
}
