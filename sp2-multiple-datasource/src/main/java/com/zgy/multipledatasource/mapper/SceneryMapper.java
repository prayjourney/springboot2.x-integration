package com.zgy.multipledatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.multipledatasource.pojo.Scenery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: z.g.y
 * @date: 2020/10/20
 * @url: https://www.cnblogs.com/aizen-sousuke/p/11756279.html, https://github.com/baomidou/dynamic-datasource-spring-boot-starter
 * @description: @DS可以注解在方法上和类上，同时存在方法注解优先于类上注解。注解在service实现或mapper接口方法上，不要同时在service和mapper注解。
 */
// @Mapper标志Mapper文件, @Repository标志DAO，有了这个就没有注入错误了
@Mapper
@Repository
@DS("db2")
public interface SceneryMapper extends BaseMapper<Scenery> {
}

