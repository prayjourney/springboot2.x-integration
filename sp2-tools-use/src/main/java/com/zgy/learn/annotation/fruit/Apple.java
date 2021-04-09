package com.zgy.learn.annotation.fruit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author renjiaxin
 * @date 2021/4/9
 */
@Getter
@Setter
@ToString
public class Apple {
    private String kind;
    private String[] origin;
    private Integer price;
}
