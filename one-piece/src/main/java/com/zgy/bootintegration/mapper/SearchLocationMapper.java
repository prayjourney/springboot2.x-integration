package com.zgy.bootintegration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.bootintegration.pojo.SearchLocation;
import org.springframework.stereotype.Repository;

/**
 * @author: z.g.y
 * @date: 2020/6/8
 * @description:
 */
@Repository
public interface SearchLocationMapper extends BaseMapper<SearchLocation> {
    int deleteLocation(String name);
}
