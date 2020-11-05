package com.zgy.learn.bootshiro.service;

import com.zgy.learn.bootshiro.mapper.PersonMapper;
import com.zgy.learn.bootshiro.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zuiguangyin
 * @Date 2020/11/5
 * @Description
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Override
    public Person queryPersonByName(String name) {
        return personMapper.queryPersonByName(name);
    }
}
