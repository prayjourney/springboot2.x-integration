package com.zgy.learn.webtoken.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.learn.webtoken.pojo.Kid;
import com.zgy.learn.webtoken.service.KidService;
import com.zgy.learn.webtoken.service.TokenJjwtService;
import com.zgy.learn.webtoken.util.JjwtConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zgy
 * @date 2021/1/13
 * @description
 */
@Slf4j
@Controller
public class KidController {
    @Autowired
    KidService kidService;

    @Autowired
    TokenJjwtService tokenService;

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
    public String login(Kid kid, HttpServletResponse response) throws JSONException, JsonProcessingException {
        Kid origin = kidService.findKidById(kid.getId());

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
            String token = tokenService.createJWT(JjwtConstant.JWT_ID, kid, JjwtConstant.JWT_TTL);

            // 把token添加到了cookie之中
            Cookie cookie = new Cookie("jwtToken", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            log.info("token: {}", token);
            return "redirect:/index";
        }
    }
}

