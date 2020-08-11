package com.zgy.learn.bootvue.controller;

import com.zgy.learn.bootvue.pojo.User;
import com.zgy.learn.bootvue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;


    @ResponseBody
    @RequestMapping("findall")
    @CrossOrigin  // 跨域
    public List<User> findAll() {
        return service.findAll();
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("saveuser") // @RequestBody是必须的
    public Map<String, Object> saveUser(@RequestBody User user) {
        Map<String, Object> mp = new HashMap<>();
        if (null == user) {
            mp.put("sucess", false);
            mp.put("message", "用户信息不合法！");
            log.error("输入的user是null!");
        }
        Integer result = service.saveUser(user);
        if (result == 1) {
            mp.put("sucess", true);
            log.info("用户保存成功!");
        } else {
            mp.put("sucess", false);
            mp.put("message", "用户保存失败！");
            log.error("用户保存失败!");
        }
        return mp;
    }


    @CrossOrigin
    @ResponseBody
    @GetMapping("deleteuserbyid")
    public Map<String, Object> deleteUserById(@Param("id") Integer id) {
        Map<String, Object> mp = new HashMap<>();
        if (null != id) {
            Integer result = service.deleteUserById(id);
            if (result >= 1) {
                log.error("id为{}的用户删除成功！", id);
                mp.put("sucess", true);
                mp.put("message", "用户删除成功！");
            } else {
                mp.put("sucess", false);
                mp.put("message", "用户删除失败！");
            }
        }else{
            mp.put("sucess", false);
            mp.put("message", "输入的用户id不合法！");
        }
        return mp;
    }
}
