package com.zgy.bootintegration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: z.g.y
 * @date: 2020/8/6
 * @description: 生成cookie, 读取Cookie, https://blog.csdn.net/qq_35387940/article/details/83501886
 * https://www.cnblogs.com/Johnfx-home/p/7125847.html，https://blog.csdn.net/qq_26321411/article/details/80665162
 */
@Controller
@RequestMapping("cookie")
@Slf4j
public class CookieContorller {

    /**
     * 创建cookie
     *
     * @param response
     * @return str
     */
    @GetMapping("create")
    @ResponseBody
    public String createCookie(HttpServletResponse response) {
        log.info("正在创建Cookie");
        Cookie c1 = new Cookie("name", "zhangsan");
        Cookie c2 = new Cookie("hobby", "跳舞");
        response.addCookie(c1);
        response.addCookie(c2);
        return "创建cookie， 请通过F12在network之中查看";
    }

    /**
     * 获取cookie
     *
     * @param request
     * @return str
     */
    @GetMapping("get")
    @ResponseBody
    public String getCookie(HttpServletRequest request) {
        log.info("正在获取Cookie");
        StringBuilder cookieStr = new StringBuilder();
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            cookieStr.append(cookies[i].getName() + ", " + cookies[i].getValue() + "; ");
        }
        return "cookie的信息是: " + cookieStr.toString();
    }

    /**
     * 注解方式获取cookie中对应的key值
     *
     * @param JSESSIONID
     * @return str
     */
    @GetMapping("/annoget")
    @ResponseBody
    public String getCookieValue(@CookieValue("JSESSIONID") String JSESSIONID) {
        log.info("通过注解获取Cookie的值");
        //前提是已经创建了或者已经存在cookie了，那么下面这个就直接把对应的key值拿出来了。
        String str = "JSESSIONID: " + JSESSIONID;
        return str;
    }
}
