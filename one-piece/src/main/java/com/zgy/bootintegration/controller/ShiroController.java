package com.zgy.bootintegration.controller;

import com.zgy.bootintegration.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/6/14 22:50
 * @modified:
 */
@RequestMapping("/shiro")
@Controller
@Slf4j
public class ShiroController {

    @RequestMapping(value = {"/index", "/test"})
    @ResponseBody
    public String shiroIndex() {
        return "hello shiro";
    }

    @RequestMapping("addmsg")
    public String add() {
        return "addmsg";
    }

    @RequestMapping("getmsg")
    public String getMsg() {
        return "getmsg";
    }

    @GetMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginCheck(String user, String password) {
        // 简单的测试
        /**
         if (null == user || null == password) {
         return "404";
         } else if ("123456".equals(password)) {
         return "index";
         } else {
         return "login";
         }
         */
        // 获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户登录的数据
        UsernamePasswordToken token = new UsernamePasswordToken(user, password);
        try {
            // 执行登录方法，没有异常说明okay
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e1) {
            log.error("用户名错误！ {} !", e1.getCause());
            return "login";
        } catch (IncorrectCredentialsException e2) {
            log.error("密码错误！ {} !", e2.getCause());
            return "login";
        }
    }

    @RequestMapping("unauthor")
    // @ResponseBody
    public String unauthor() {
        // return "页面未经授权，不得访问！";
        return "unauthor";
    }

    // 权限的检测
    @RequiresPermissions("user:money")
    @GetMapping("getmoney")
    public String getMoney() {
        try {
            // 问题，如果没有登录，则直接跳到登录页面，如果没有权限，则跳到未授权页面，不应该在这儿再次处理了吧？
            // ShiroConfig simpleMappingExceptionResolver解决了这个问题
            // Subject subject = SecurityUtils.getSubject(); // 权限是一开始就回去检测和检查, 根本不应该来这儿操作
            System.out.println("获取1000元!");
            return "getmoney";
        } catch (AuthorizationException e) {
            // 权限是一开始就回去检测和检查, 根本不应该来这儿操作
            System.out.println(e.getCause());
            return "login";
        }
    }

    // 角色的检测
    @RequiresRoles("role:admin")
    @GetMapping("getallinfo")
    public String getAllInfo() {
        System.out.println("我是管理，想干啥就干啥！");
        return "allinfo";
    }

    @GetMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (null != user) {
            subject.logout();
            log.warn("退出成功");
            return "index";
        } else {
            log.warn("没有用户登陆，不需要退出");
            return "index";
        }
    }
}
