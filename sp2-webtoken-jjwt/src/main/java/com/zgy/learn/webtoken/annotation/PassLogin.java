package com.zgy.learn.webtoken.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-01-18
 * @modified:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PassLogin {
    boolean required() default true;
}
