package com.zgy.learn.pagination.mapper;

import com.zgy.learn.pagination.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 无条件分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<City> getByPageNumSize(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 条件分页
     *
     * @param ctId
     * @param ctName
     * @param ctProvince
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<City> getByConditionPageNumSize(@Param("ctId") Integer ctId,
                                         @Param("ctName") String ctName,
                                         @Param("ctProvince") String ctProvince,
                                         @Param("pageNum") Integer pageNum,
                                         @Param("pageSize") Integer pageSize);

    /**
     * 条件分页, 通过对象传参
     *
     * @param city
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<City> getByObjectConditionPageNumSize(@Param("city") City city,
                                               @Param("pageNum") Integer pageNum,
                                               @Param("pageSize") Integer pageSize);

}
