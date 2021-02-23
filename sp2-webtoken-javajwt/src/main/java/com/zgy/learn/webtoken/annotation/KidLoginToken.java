package com.zgy.learn.webtoken.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: z.g.y
 * @date: 2021/1/13
 * @description: 用于登录后才能操作的token
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface KidLoginToken {
    boolean required() default true;
}
