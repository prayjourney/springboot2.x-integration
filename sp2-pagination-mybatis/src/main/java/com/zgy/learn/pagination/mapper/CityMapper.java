package com.zgy.learn.pagination.mapper;

import com.zgy.learn.pagination.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-04-03
 * @modified:
 */
@Repository
@Mapper
public interface CityMapper {
    City getById(Integer ctId);

    List<City> getAllCities();

}
