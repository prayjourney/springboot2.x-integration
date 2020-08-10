package com.zgy.learn.bootvue.controller;

import com.zgy.learn.bootvue.pojo.User;
import com.zgy.learn.bootvue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/8/5 1:25
 * @Modified by:
 */
@Slf4j
@Controller
@CrossOrigin  // 跨域
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;


    @ResponseBody
    @RequestMapping("findall")
    public List<User> findAll() {
        return service.findAll();
    }


    @ResponseBody
    @PostMapping("saveuser") // @RequestBody是必须的
    public Map<String, Object> saveUser(@RequestBody User user) {
        Map<String, Object> mp = new HashMap<>();
        if (null == user) {
            mp.put("sucess", false);
            mp.put("message", "用户保存失败！");
            log.error("输入的user是null!");
        }
        Integer result = service.saveUser(user);
        if (result == 1){
            mp.put("sucess", true);
            log.info("用户保存成功!");
        }else {
            mp.put("sucess", false);
            mp.put("message", "用户保存失败！");
            log.error("用户保存失败!");
        }
        return mp;
    }
}
