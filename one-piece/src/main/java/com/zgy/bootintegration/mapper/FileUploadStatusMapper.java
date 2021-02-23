package com.zgy.bootintegration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.bootintegration.pojo.FileUploadStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: z.g.y
 * @date: 2020/10/12
 * @description:
 */
@Mapper
@Repository
public interface FileUploadStatusMapper extends BaseMapper<FileUploadStatus> {
    int selectFileMd5(String md5);

    FileUploadStatus selectFileMd5Obj(String md5);
}
