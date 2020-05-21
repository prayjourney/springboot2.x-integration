package com.wm.zgy.bootmybatismbplusshiroes.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: renjiaxin
 * @Despcription: 数学老师
 * @Date: Created in 2020/5/22 2:39
 * @Modified by:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MathTeacher {
    private Integer id;
    private String name;
    private String gender;
    private String hobby;
}
