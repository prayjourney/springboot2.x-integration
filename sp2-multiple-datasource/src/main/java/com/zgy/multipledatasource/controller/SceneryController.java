package com.zgy.multipledatasource.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.multipledatasource.pojo.Scenery;
import com.zgy.multipledatasource.service.SceneryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author renjiaxin
 * @Date 2020/10/20
 * @Description
 */
@RestController
@Slf4j
@RequestMapping(value = "scenery")
public class SceneryController {
    @Autowired
    private SceneryService sceneryService;

    @RequestMapping("info")
    public Scenery selectSceneryById(Integer id) {
        log.info("selectSceneryById");
        return sceneryService.selectSceneryById(id);
    }

    @RequestMapping("delete")
    public int deleteSceneryById(Integer id) {
        log.info("deleteSceneryById");
        return sceneryService.deleteSceneryById(id);
    }

    @RequestMapping("country")
    public String selectSceneryCountryById(Integer id) {
        log.info("selectSceneryCountryById");
        return sceneryService.selectSceneryCountryById(id);
    }

    @RequestMapping("name")
    public String getSceneryNameById(Integer id) {
        log.info("getSceneryNameById");
        return sceneryService.getSceneryNameById(id);
    }
}
