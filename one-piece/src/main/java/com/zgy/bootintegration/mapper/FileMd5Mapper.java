package com.zgy.bootintegration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.bootintegration.pojo.FileMd5;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: z.g.y
 * @date: 2020/10/12
 * @description: 可以同时加上@Mapper和@Repository，一个是给mybatis看，一个是给spring看，这样就不会报错了
 */
@Mapper
@Repository
public interface FileMd5Mapper extends BaseMapper<FileMd5> {
    int selectFileMd5(String md5);

    String selectFilePath(String md5);
}
