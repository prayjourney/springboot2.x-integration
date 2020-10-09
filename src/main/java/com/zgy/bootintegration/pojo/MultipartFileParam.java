package com.zgy.bootintegration.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author renjiaxin
 * @Date 2020/10/9
 * @Description 分块文件传输对象
 */
@Setter
@Getter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MultipartFileParam {
    // 文件传输任务ID
    private String taskId;

    // 当前为第几分片
    private int chunk;

    // 每个分块的大小
    private long size;

    // 分片总数
    private int chunkTotal;

    // 主体类型--这个字段是我项目中的其他业务逻辑可以忽略
    private int objectType;

    // 分块文件传输对象
    private MultipartFile file;
}
