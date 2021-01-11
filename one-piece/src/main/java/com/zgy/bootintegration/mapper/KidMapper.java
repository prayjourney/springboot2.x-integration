package com.zgy.bootintegration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.bootintegration.pojo.Kid;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/8/9 12:50
 * @Modified by:
 */
@Mapper
@Repository
public interface KidMapper extends BaseMapper<Kid> {
}
