package com.zgy.bootintegration.controller;

import com.zgy.bootintegration.annotation.KidLoginToken;
import com.zgy.bootintegration.pojo.Kid;
import com.zgy.bootintegration.service.KidService;
import com.zgy.bootintegration.service.TokenService;
import com.zgy.bootintegration.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/8/9 12:29
 * @Modified by:
 */
@Slf4j
@RestController
@RequestMapping("kidtoken")
public class KidTokenController {
    @Autowired
    KidService kidService;
    @Autowired
    TokenService tokenService;

    /**
     * 登录
     *
     * @param kid
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(Kid kid, HttpServletResponse response) throws JSONException {
        Map<String, Object> tokenMap = new HashMap<>();
        Kid origin = kidService.findKidById(kid.getId());
        Kid kid001 = new Kid();

        kid001.setId(origin.getId());
        kid001.setUsername(origin.getUsername());
        kid001.setPassword(origin.getPassword());
        if (!kid001.getPassword().equals(kid.getPassword())) {
            tokenMap.put("message", "登录失败,密码错误");
            return tokenMap;
        } else {
            String token = tokenService.getToken(kid001);
            tokenMap.put("token", token);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);

            return tokenMap;
        }
    }

    /**
     * 这个请求需要验证token才能访问
     */
    @KidLoginToken
    @RequestMapping(value = "/getmessage", method = RequestMethod.GET)
    public String getMessage() {
        // 取出token中带的用户id 进行操作
        log.info(JwtTokenUtil.getTokenUserId());
        return "您已通过验证";
    }
}
