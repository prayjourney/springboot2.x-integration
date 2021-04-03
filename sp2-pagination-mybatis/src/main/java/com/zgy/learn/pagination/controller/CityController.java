package com.zgy.learn.pagination.controller;

import com.github.pagehelper.PageInfo;
import com.zgy.learn.pagination.pojo.City;
import com.zgy.learn.pagination.service.CityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-04-03
 * @modified:
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/get/all")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/get/id")
    public City getById(Integer ctId) {
        return cityService.getById(ctId);
    }

    @GetMapping("/get/page")
    public PageInfo getByPage(Integer pageNum, Integer pageSize) {
        return cityService.getByPageNumSize(pageNum, pageSize);
    }

    @GetMapping("/get/page/condition")
    public PageInfo getByConditionPage(Integer ctId, String ctName, String ctProvince, Integer pageNum, Integer pageSize) {
        if (null == ctId || "".equals(ctId)) {
            ctId = null;
        }
        if (StringUtils.isBlank(ctName)) {
            ctName = null;
        }
        if (StringUtils.isBlank(ctProvince)) {
            ctProvince = null;
        }
        return cityService.getByConditionPageNumSize(ctId, ctName, ctProvince, pageNum, pageSize);
    }

    @GetMapping("/get/page/obj/condition")
    public PageInfo getByObjectConditionPage(Integer ctId, String ctName, String ctProvince, Integer pageNum, Integer pageSize) {
        City city = new City();
        city.setCtId(ctId).setCtName(ctName).setCtProvince(ctProvince);
        if (null == ctId || "".equals(ctId)) {
            city.setCtId(null);
        }
        if (StringUtils.isBlank(ctName)) {
            city.setCtName(null);
        }
        if (StringUtils.isBlank(ctProvince)) {
            city.setCtProvince(null);
        }
        return cityService.getByObjectConditionPageNumSize(city, pageNum, pageSize);
    }

}
