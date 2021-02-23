package com.zgy.bootintegration.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: z.g.y
 * @description: 用于登录后才能操作的token
 * @date: Created in 2020/8/9 12:24
 * @modified: 自定义注解KidLoginToken
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface KidLoginToken {
    boolean required() default true;
}