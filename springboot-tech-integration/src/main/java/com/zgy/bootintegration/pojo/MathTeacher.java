package com.zgy.bootintegration.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: renjiaxin
 * @Despcription: 数学老师
 * @Date: Created in 2020/5/22 2:39
 * @Modified by:
 */
@Setter
@Getter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MathTeacher {
    private Integer id;
    private String name;
    private String gender;
    private String hobby;
}
