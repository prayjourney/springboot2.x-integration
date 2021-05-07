package com.zgy.learn.beanutils;

import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-02-18
 * @modified:
 */
public class SpringBeanUtilsUse {
    public static void main(String[] args) {
        Animal animal01 = new Animal().setAge(5).setId(1).setKind("猫").setName("小米").setBirthday(new Date());
        Animal animal02 = new Animal();
        System.out.println("animal02: " + animal02.toString());
        BeanUtils.copyProperties(animal01, animal02);
        System.out.println("animal01: " + animal01.toString());
        System.out.println(animal01);
        System.out.println("animal02: " + animal02.toString());
        System.out.println(animal02);
        System.out.println("animal01 == animal02 : " + (animal01 == animal02));
    }
}
