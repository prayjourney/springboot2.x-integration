package com.zgy.bootintegration.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: renjiaxin
 * @Despcription: 用来跳过验证的 PassToken
 * @Date: Created in 2020/8/9 12:23
 * @Modified by:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}