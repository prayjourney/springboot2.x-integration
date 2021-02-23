package com.zgy.learn.webtoken.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author: z.g.y
 * @date: 2021/2/1
 */
@Controller
@Slf4j
public class IndexController {
    @GetMapping(value = {"", "/", "index"})
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param model
     * @return
     */
    @PostMapping("login")
    public String login(String username, String password, Model model) {
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            log.info("登录成功...");
            model.addAttribute("name", username);
            return "message";
        } catch (UnknownAccountException e1) {
            log.error("用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e2) {
            log.error("密码错误");
            return "login";
        }
    }
}
