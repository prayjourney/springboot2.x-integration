package com.zgy.learn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-01-24
 * @modified :
 */
@Controller
@RequestMapping("session")
@Slf4j
public class SessionController {
    /**
     * 添加用户与属性
     *
     * @param request
     * @param name
     * @param value
     * @return str
     */
    @GetMapping("/add/{name}/{value}")
    @ResponseBody
    public String addSession(HttpServletRequest request, @PathVariable("name") String name, @PathVariable("value") String value) {
        HttpSession session = request.getSession();
        session.setAttribute(name, value);
        return "sessionId: " + session.getId() + " name:" + name;
    }

    /**
     * 获取sessionid
     *
     * @param request
     * @param name
     * @return str
     */
    @GetMapping("/get/{name}")
    @ResponseBody
    public String getSesseion(HttpServletRequest request, @PathVariable("name") String name) {
        HttpSession session = request.getSession();
        String value = (String) session.getAttribute(name);
        return "sessionId:" + session.getId() + " value:" + value;
    }
}
