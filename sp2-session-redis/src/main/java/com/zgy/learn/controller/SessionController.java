package com.zgy.learn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-24
 * @modified:
 */
@Controller
@RequestMapping("sessiontest")
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
    public String createSession(HttpServletRequest request) {
        log.info("如果是第一次请求，那就创建session");
        // true表示如果这个http请求中有session, 则直接通过getSession获取当前的session, 若没有session, 则会自动新建一个session

        HttpSession session = request.getSession(true);
        session.setAttribute("username", "TOM");
        session.setAttribute("password", "tommmm");
        return "已经创建好了session, 给前端返回了session id";
    }


    /**
     * 创建session, 并且给前端返回sessionID
     *
     * @param request
     * @return str
     */
    @GetMapping("create2browser")
    @ResponseBody
    public String createSession2Browser(HttpServletRequest request, HttpServletResponse response) {
        log.info("如果是第一次请求，那就创建session");
        HttpSession session = request.getSession(true);
        session.setAttribute("username", "TOM");
        session.setAttribute("password", "tommmm");
        Cookie c1 = new Cookie("name", "zhangsan");
        response.addCookie(c1);
        return "已经创建好了session, 给前端返回了session id, cookie值，请通过F12在network之中查看";
    }


    /**
     * 获取session, 查看sessionid和前端返回的是否一致
     * HttpSession session=request.getSession(flag),flag为flase或者为空，就不去创建session, 这时如果去获取，就会为null
     * Session能不能取到和是否持久化无关，只是和：HttpSession session=request.getSession(ture); 之前有没有调用这句话有关系
     * 不管有没有持久化，session只要创建了就会存在，如果没有创建就不存在，就会为null, 和是否持久化无关, 体现了session确实是在
     * 服务器的内存之中存在的
     *
     * @param request
     * @return str
     */
    @GetMapping("get")
    @ResponseBody
    public String getSession(HttpServletRequest request) {
        log.info("如果是第一次请求，那就创建session");
        HttpSession session = request.getSession(false);
        if (null != session) {
            String sessionId = session.getId();
            String userName = session.getAttribute("username").toString();
            log.info("从session之中获取sessionId:" + sessionId);
            log.info("从session之中获取userName:" + userName);

            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                String value = cookies[i].getValue();
                log.info("从cookie之中获取key：" + name);
                log.info("从cookie之中获取value:" + value);
                if (sessionId.equals(value)) {
                    log.info("前端传来的sessionID和后端的sessionID相等!" + sessionId + "," + value);
                }
            }
            return "已经创建好了session, 给前端返回了session id, cookie值，请通过F12在network之中查看";
        } else {
            return "session为空！";
        }
    }


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
    public String getSession(HttpServletRequest request, @PathVariable("name") String name) {
        HttpSession session = request.getSession();
        String value = (String) session.getAttribute(name);
        return "sessionId:" + session.getId() + " value:" + value;
    }
}