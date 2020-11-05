package com.zgy.learn.bootshiro.service;

import com.zgy.learn.bootshiro.pojo.Person;

/**
 * @Author zuiguangying
 * @Date 2020/11/5
 * @Description
 */
public interface PersonService {
    public Person queryPersonByName(String name);
}
