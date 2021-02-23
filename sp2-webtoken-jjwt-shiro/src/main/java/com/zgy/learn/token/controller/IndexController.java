package com.zgy.learn.token.controller;

import com.zgy.learn.token.pojo.OpUser;
import com.zgy.learn.token.service.OpUserService;
import com.zgy.learn.token.shiro.JwtToken;
import com.zgy.learn.token.util.JwtTokenUtil;
import com.zgy.learn.token.util.result.Result;
import com.zgy.learn.token.util.result.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: z.g.y
 * @date: 2021/2/1
 */
@Controller
@Slf4j
public class IndexController {
    @Autowired
    OpUserService opUserService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping(value = {"", "/", "index"})
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    /**
     * 登录, 直接使用数据库的时候这么操作
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

    /**
     * 登录, 使用jwt的时候直接这么操作, 本质其实就是验证用户+密码
     *
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("logintoken")
    public Result<String> logintoken(String username, String password) throws Exception {
        if (null == username || null == password) {
            throw new Exception("username null, error!");
        }

        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 查询用户是否存在, 获取salt等信息
        OpUser opUser = opUserService.queryByName(username);
        if (null == opUser) {
            throw new UnknownAccountException();
        }
        // 生成token
        opUser.setPassword("");
        String token = jwtTokenUtil.createToken(opUser, password);
        JwtToken jwtToken = new JwtToken(token);

        try {
            // 登录
            subject.login(jwtToken);
            log.info("登录成功...");
            // 返回token
            String loginToken = jwtTokenUtil.createToken(opUser, null);
            return new Result<String>(Status.OKAY, "login success!", loginToken);
        } catch (Exception e) {
            return new Result<String>(Status.ERROR, "login error!");
        }
    }
}
