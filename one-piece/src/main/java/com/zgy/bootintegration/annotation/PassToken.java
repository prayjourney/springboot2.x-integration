package com.zgy.bootintegration.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: z.g.y
 * @description: 用来跳过验证的 PassToken
 * @date: Created in 2020/8/9 12:23
 * @modified: 自定义注解PassToken
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}