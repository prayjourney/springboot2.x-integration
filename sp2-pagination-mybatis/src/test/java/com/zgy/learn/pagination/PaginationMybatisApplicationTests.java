package com.zgy.learn.pagination;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zgy.learn.pagination.mapper.CityMapper;
import com.zgy.learn.pagination.pojo.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PaginationMybatisApplicationTests {
    @Autowired
    private CityMapper cityMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetCities() {
        PageHelper.startPage(1, 5);
        List<City> allCities = cityMapper.getAllCities();
        Page<City> cityPage = (Page<City>) allCities;
        System.out.println(cityPage.getTotal());
        System.out.println(cityPage.getPageSize());

    }

    @Test
    public void testGetCities02() {
        PageHelper.startPage(1, 5);
        List<City> allCities = cityMapper.getAllCities();
        Page<City> cityPage = (Page<City>) allCities;
        System.out.println(cityPage.setPageNum(2));
        System.out.println(cityPage.getResult());

    }

}
