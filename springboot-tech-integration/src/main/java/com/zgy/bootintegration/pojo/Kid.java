package com.zgy.bootintegration.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/8/9 12:49
 * @Modified by: Kid的实体
 */
@Setter
@Getter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Kid {
    Integer id;
    String username;
    String password;
}