package com.zgy.multipledatasource.mapper.db2;

import com.zgy.multipledatasource.pojo.Scenery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zgy
 * @date 2020/10/20
 * @description
 * @url https://www.cnblogs.com/aizen-sousuke/p/11756279.html
 */
@Mapper
@Repository
public interface SceneryMapper {
    @Select("select * from scenery")
    List<Scenery> selectAll();

    Scenery selectById(Integer id);

    int deleteById(Integer id);

    String selectSceneryCountryById(Integer id);

    String getSceneryNameById(Integer id);
}

