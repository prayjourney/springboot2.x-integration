package com.zgy.learn.pagination.controller;

import com.github.pagehelper.PageInfo;
import com.zgy.learn.pagination.pojo.City;
import com.zgy.learn.pagination.service.CityService;
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

}
