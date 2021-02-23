package com.zgy.bootintegration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.bootintegration.pojo.Kid;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/8/9 12:50
 * @modified:
 */
@Mapper
@Repository
public interface KidMapper extends BaseMapper<Kid> {
}
