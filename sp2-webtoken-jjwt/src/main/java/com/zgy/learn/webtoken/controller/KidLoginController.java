package com.zgy.learn.webtoken.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.learn.webtoken.annotation.NeedLogin;
import com.zgy.learn.webtoken.annotation.PassLogin;
import com.zgy.learn.webtoken.pojo.Kid;
import com.zgy.learn.webtoken.service.KidService;
import com.zgy.learn.webtoken.service.TokenJjwtService;
import com.zgy.learn.webtoken.util.JjwtConstant;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author: zgy
 * @date: 2021/1/13
 * @description:
 */
@Slf4j
@Controller
public class KidLoginController {
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
            String token = tokenService.createJWT(JjwtConstant.JWT_ID, kid, JjwtConstant.JWT_TTL);

            // 1. 把token添加到了cookie之中
            Cookie cookie = new Cookie("jwtToken", token);
            cookie.setPath("/");
            cookie.setHttpOnly(true); // httponly, 安全一些
            response.addCookie(cookie);

            // 2. 把token添加到了header之中
            // response.setHeader(JjwtConstant.AUTH_HEADER_KEY, JjwtConstant.JWT_TOKEN_PREFIX + token);
            response.setHeader(JjwtConstant.AUTH_HEADER_KEY, token); // 暂时注销Bearer Token前缀
            log.info("token: {}", token);

            // 跳转的事情, 还是让前端拿到响应结果后自己跳转
            // return "redirect:/index";
            return "tokenHtml";
        }
    }

    @NeedLogin
    @ResponseBody
    @GetMapping(value = "/kid/needlogincookie")
    public String needLoginCookie(HttpServletRequest request) {
        String token = null;
        // 1. 把token添加到了cookie之中, 从header之中携带的cookie获取信息
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("jwtToken")) {
                token = cookies[i].getValue();
            }
        }
        if (null != token) {
            return "有token信息，暂时通过";
        } else {
            return "没有token信息，无法通过";
        }
    }

    @NeedLogin
    @ResponseBody
    @GetMapping(value = "/kid/senddata")
    public String getDataByHeader(HttpServletRequest request) {
        String token = null;
        // 前端从header之中获取传入数据, 后端获取
        // 传入了music, ide, token没有传, 所以为null
        token = request.getHeader("Authorization");
        String music = request.getHeader("music");
        String ide = request.getHeader("ide");
        log.info("token: {}, music: {}, ide: {}！", token, music, ide);
        if (null != token) {
            return "have token, pass!";
        } else {
            return "no token, forbidden!";
        }
    }

    @NeedLogin
    @ResponseBody
    @GetMapping(value = "/kid/needloginheader")
    // 直接在request之中获取header信息即可
    // public String needLoginHeader(HttpServletRequest request, @RequestHeader HttpHeaders headers) {
    public String needLoginHeader(HttpServletRequest request) {
        String token = null;
        // 2. 把token添加到了header之中, 从header的之中获取
        token = request.getHeader("Authorization");
        log.info("token: {}！", token);

        // token有效性的验证, 时间都是毫秒
        Long nowTime = System.currentTimeMillis();
        Claims claims = tokenService.parseJWT(token);
        Date expiration = claims.getExpiration();
        Long expTime = expiration.getTime();
        if (null != token) {
            if (nowTime - expTime <= 0) {
                return "valid";
            } else {
                return "invalid";
            }
        } else {
            return "notoken";
        }
    }


    @PassLogin
    @ResponseBody
    @GetMapping(value = "/kid/passlogin")
    public String passLogin() {
        return "不需要验证";
    }

}

