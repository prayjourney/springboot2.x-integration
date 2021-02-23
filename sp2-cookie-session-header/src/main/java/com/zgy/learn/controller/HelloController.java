package com.zgy.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: z.g.y
 * @date: 2021/1/18
 */
@RestController
public class HelloController {
    @GetMapping("hello1")
    public String hello1(HttpServletRequest request, HttpServletResponse response) {
        // 设置header信息
        response.setHeader("e1", "111");
        response.setHeader("e2", "222");
        response.addHeader("e3", "333");
        response.addHeader("e4", "444");
        // 设置attribute
        request.setAttribute("a1", "a1");
        request.setAttribute("a2", "a2");

        // 设置cookie
        response.addCookie(new Cookie("c1", "c1"));
        response.addCookie(new Cookie("c2", "c2"));
        return "F12查看";
    }

    @GetMapping("hello2")
    public String hello2(HttpServletRequest request) {
        // 打印header中的信息
        System.out.println("e1:   " + request.getHeader("e1"));
        System.out.println("e2:   " + request.getHeader("e2"));
        System.out.println("e3:   " + request.getHeader("e3"));
        System.out.println("e4:   " + request.getHeader("e4"));
        System.out.println("Host:   " + request.getHeader("Host"));
        System.out.println("User-Agent:   " + request.getHeader("User-Agent"));

        // 打印attribute的信息
        System.out.println("a1:   " + request.getAttribute("a1"));
        System.out.println("a2:   " + request.getAttribute("a2"));

        // 打印cookie的信息
        Cookie[] cookies = request.getCookies();
        System.out.println("c1:   " + cookies[0].getValue());
        System.out.println("c2:   " + cookies[1].getValue());

        return "F12查看";
    }

    @GetMapping("hello3")
    public String hello3() {
        return "hello3";
    }
}
