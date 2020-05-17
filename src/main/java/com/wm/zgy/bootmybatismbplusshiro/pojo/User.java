package com.wm.zgy.bootmybatismbplusshiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/5/17 21:41
 * @Modified by:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
