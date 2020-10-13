package com.zgy.bootintegration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.bootintegration.pojo.FileMd5;
import org.springframework.stereotype.Repository;

/**
 * @Author renjiaxin
 * @Date 2020/10/12
 * @Description
 */
@Repository
public interface FileMd5Mapper extends BaseMapper<FileMd5> {
    int selectFileMd5(String md5);

    String selectFilePath(String md5);
}
