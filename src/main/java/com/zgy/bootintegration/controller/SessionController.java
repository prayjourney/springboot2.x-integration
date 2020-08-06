package com.zgy.bootintegration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author renjiaxin
 * @Date 2020/8/6
 * @Description
 */
@Controller
@RequestMapping("session")
@Slf4j
public class SessionController {
    /**
     * 创建session
     *
     * @param request
     * @return str
     */
    @GetMapping("create")
    @ResponseBody
    public String cookie(HttpServletRequest request) {
        log.info("如果是第一次请求，那就创建session");
        // true表示如果这个HTTP请求中有session，则直接通过getSession获取当前的session，若没有session，则会自动新建一个session
        HttpSession session = request.getSession(true);
        // false表示只能获取当前请求中的session，如果没有也不能自动创建。
        // HttpSession session=request.getSession(false);

        session.setAttribute("username","TOM");
        session.setAttribute("password","tommmm");
        return "已经创建好了session, 给前端返回了session id";
    }
}