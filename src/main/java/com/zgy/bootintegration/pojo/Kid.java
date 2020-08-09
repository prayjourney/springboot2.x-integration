package com.zgy.bootintegration.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/8/9 12:49
 * @Modified by:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kid {
    Integer id;
    String username;
    String password;
}