package com.zgy.learn.webtoken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.webtoken.pojo.Kid;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author z.g.y
 * @date 2021/1/13
 * @description
 */
@Mapper
@Repository
public interface KidMapper extends BaseMapper<Kid> {
}
