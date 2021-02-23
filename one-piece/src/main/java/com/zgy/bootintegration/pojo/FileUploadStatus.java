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
 * @author: z.g.y
 * @date: 2020/10/12
 * @description:
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
