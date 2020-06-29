package com.zgy.bootintegration.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Author renjiaxin
 * @Date 2020/6/23
 * @Description 测试序列化
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatSerialize implements Serializable {
    private static final long serialVersionUID = 12345L;
    private Integer id;
    private String name;
    private String kind;
    private LocalDate birthday;
}
