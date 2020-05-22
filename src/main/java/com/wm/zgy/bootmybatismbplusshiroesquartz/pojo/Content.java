package com.wm.zgy.bootmybatismbplusshiroesquartz.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: renjiaxin
 * @Despcription: 内容
 * @Date: Created in 2020/5/23 1:29
 * @Modified by:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String title;
    private String img;
    private String price;
}
