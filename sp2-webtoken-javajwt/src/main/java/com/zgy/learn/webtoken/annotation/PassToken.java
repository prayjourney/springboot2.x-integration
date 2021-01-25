package com.zgy.learn.webtoken.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author z.g.y
 * @Date 2021/1/13
 * @Description 用来跳过验证的 PassToken
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
