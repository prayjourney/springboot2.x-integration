package com.wm.zgy.bootmybatismbplusshiroesquartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wm.zgy.bootmybatismbplusshiroesquartz.pojo.SearchLocation;
import org.springframework.stereotype.Repository;

/**
 * @Author renjiaxin
 * @Date 2020/6/8
 * @Description
 */
@Repository
public interface SearchLocationMapper extends BaseMapper<SearchLocation> {
    int deleteLocation(String name);
}
