package com.zgy.learn.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

/**
 * 分片上传包装
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MultipartFileParam {
    // 用户id
    private String uid;
    // 任务ID
    private String id;
    // 总分片数量
    private int chunks;
    // 当前为第几块分片
    private int chunk;
    // 当前分片大小
    private long size = 0L;
    // 文件名
    private String name;
    // 分片对象
    private MultipartFile file;
    // MD5值
    private String md5;

}
