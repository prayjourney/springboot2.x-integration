package com.zgy.learn.bootshiro.controller;

import com.zgy.learn.bootshiro.pojo.Person;
import com.zgy.learn.bootshiro.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author renjiaxin
 * @Date 2020/11/6
 * @Description
 */
@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @RequestMapping("querybyname")
    public Person queryPersonByName(String name) {
        return personService.queryPersonByName(name);
    }
}
