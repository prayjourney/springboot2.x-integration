package com.zgy.multipledatasource.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.multipledatasource.mapper.SceneryMapper;
import com.zgy.multipledatasource.pojo.Scenery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: z.g.y
 * @date: 2020/10/20
 * @description:
 */
@Service
@Slf4j
public class SceneryService {
    @Autowired
    private SceneryMapper sceneryMapper;

    public Scenery selectSceneryById(Integer id) {
        log.info("selectSceneryById, SceneryMapper!");
        return sceneryMapper.selectById(id);
    }

    public int deleteSceneryById(Integer id) {
        log.info("deleteSceneryById, SceneryMapper!");
        return sceneryMapper.deleteById(id);
    }

    public String selectSceneryCountryById(Integer id) {
        log.info("selectSceneryCountryById, SceneryMapper!");
        return sceneryMapper.selectById(id).getCountry();
    }

    public String getSceneryNameById(Integer id) {
        log.info("getSceneryNameById, SceneryMapper!");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        return sceneryMapper.selectOne(wrapper).getName();
    }
}
