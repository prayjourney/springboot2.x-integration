package com.zgy.learn.annotation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author: z.g.y
 * @date: 2021/4/7
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Computer {
    @MyAnnotation(value = "hp")
    private String brand;
    @MyAnnotation("envy")
    private String kind;
    @MyAnnotation("9998")
    private Integer price;
}
