package com.zgy.bootintegration.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.bootintegration.pojo.FileUploadStatus;
import org.springframework.stereotype.Repository;

/**
 * @Author renjiaxin
 * @Date 2020/10/12
 * @Description
 */
@Repository
public interface FileUploadStatusMapper extends BaseMapper<FileUploadStatus> {
    int selectFileMd5(String md5);

    FileUploadStatus selectFileMd5Obj(String md5);
}
