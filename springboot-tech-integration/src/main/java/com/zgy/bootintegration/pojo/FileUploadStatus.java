package com.zgy.bootintegration.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Author renjiaxin
 * @Date 2020/10/12
 * @Description
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class FileUploadStatus {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String fileMd5;
    private String uploadStatus;
}
