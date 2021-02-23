package com.zgy.learn.webtoken.controller;

import com.zgy.learn.webtoken.annotation.KidLoginToken;
import com.zgy.learn.webtoken.annotation.PassToken;
import com.zgy.learn.webtoken.pojo.Kid;
import com.zgy.learn.webtoken.service.KidService;
import com.zgy.learn.webtoken.service.TokenService;
import com.zgy.learn.webtoken.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zgy
 * @date: 2021/1/13
 * @description:
 */
@Slf4j
@Controller
public class KidController {
    @Autowired
    KidService kidService;
    @Autowired
    TokenService tokenService;

    /**
     * 到登录产生token的页面
     *
     * @return
     */
    @RequestMapping(value = "/kid/login", method = RequestMethod.GET)
    public String tokenLoginPage() {
        return "tokenHtml";
    }


    /**
     * 登录
     *
     * @param kid
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/kid/login", method = RequestMethod.POST)
    public String login(Kid kid, HttpServletResponse response) throws JSONException {
        Kid origin = kidService.findKidById(kid.getId());
        if (null == origin) {
            log.info("没有id为{}的用户！", kid.getId());
            return "redirect:/";
        }

        Kid kid001 = new Kid();
        kid001.setId(origin.getId());
        kid001.setUsername(origin.getUsername());
        kid001.setPassword(origin.getPassword());

        // 简单验证, 成功后生成token
        if (!kid001.getPassword().equals(kid.getPassword())) {
            log.error("登录失败,密码错误!");
            // 跳转到index.html页面
            return "redirect:/";
        } else {
            String token = tokenService.createToken(kid001);

            // 1. 把token添加到了cookie之中
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            // 2. 把token添加到了header之中
            // response.addHeader("token", token);

            log.info("token");
            // ①--error
            // 跳转到index.html页面, 这样没法直接跳转到http://localhost:8298/index，而是http://localhost:8298/kid/login/
            // return "index";

            // ②--error
            // 跳转到index.html页面
            // return "redirect:../index.html";

            // ③--error
            // 跳转到index.html页面, 能不能在controller之中调用另一个controller的方法, 而这个只是返回对应的页面, url没有修改
            // return "index";

            // ④--error
            // 转发, 服务器端行为, https://blog.csdn.net/yubin1285570923/article/details/83796003
            // return "forward:/index";

            // ⑤--okay
            // 重定向, 客户端行为, 重定向到这个/index页面, 相当于是url的一次调用, 刷新了url,
            return "redirect:/index";
        }
    }


    /**
     * 这个请求需要验证token才能访问
     *
     * @return
     */
    @KidLoginToken
    @ResponseBody
    @RequestMapping(value = "/kid/getmessage", method = RequestMethod.GET)
    public String getMessage() {
        // 取出token中带的用户id 进行操作
        log.info(JwtTokenUtil.getTokenKidId());
        return "您已通过验证";
    }


    /**
     * 跳过token验证
     *
     * @return
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/kid/getmessagenotoken", method = RequestMethod.GET)
    public String getMessageNoToken() {
        log.info("不需要token验证");
        return "不需要token验证";
    }
}

