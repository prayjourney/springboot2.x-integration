package com.zgy.bootintegration.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author: z.g.y
 * @date: 2020/6/23
 * @description: 测试序列化
 */
@Setter
@Getter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CatSerialize implements Serializable {
    private static final long serialVersionUID = 12345L;
    private Integer id;
    private String name;
    private String kind;
    private LocalDate birthday;
}
