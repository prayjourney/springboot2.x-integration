package com.zgy.bootintegration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author: z.g.y
 * @date: 2020/8/6
 * @description:
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
    public String createSession(HttpServletRequest request) {
        log.info("如果是第一次请求，那就创建session");
        // true表示如果这个HTTP请求中有session，则直接通过getSession获取当前的session，若没有session，则会自动新建一个session
        HttpSession session = request.getSession(true);
        // false表示只能获取当前请求中的session，如果没有也不能自动创建。
        // HttpSession session=request.getSession(false);

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
        // true表示如果这个HTTP请求中有session，则直接通过getSession获取当前的session，若没有session，则会自动新建一个session
        HttpSession session = request.getSession(true);
        // false表示只能获取当前请求中的session，如果没有也不能自动创建。
        // HttpSession session=request.getSession(false);

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
        // false表示只能获取当前请求中的session，如果没有也不能自动创建, 当没有持久化的时候，就会为null--->错误
        // 不管有没有持久化，session只要创建了就会存在，如果没有创建就不存在，就会为null, 和是否持久化无关
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
    @PostMapping("/add/{name}/{value}")
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


    /**
     * 添加用户与属性, 与上面的函数作用相同, 为了显示使用jdbc存储, 所以添加了jdbc的url
     *
     * @param request
     * @param name
     * @param value
     * @return str
     */
    @PostMapping("/jdbc/add/{name}/{value}")
    @ResponseBody
    public String addSessionJDBC(HttpServletRequest request, @PathVariable("name") String name,
                                 @PathVariable("value") String value) {
        log.info("使用jdbc存取session!");
        HttpSession session = request.getSession();
        // 看看session里面到底是什么
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            log.info(attributeNames.nextElement().toString());
        }

        // 往session里面添加内容
        session.setAttribute(name, value);
        return "sessionId: " + session.getId() + ", name:" + name;
    }


    /**
     * 获取sessionid, 与上面的函数作用相同, 为了显示使用jdbc存储, 所以添加了jdbc的url
     *
     * @param request
     * @param name
     * @return str
     */
    @GetMapping("/jdbc/get/{name}")
    @ResponseBody
    public String getSesseionJDBC(HttpServletRequest request, @PathVariable("name") String name) {
        log.info("使用jdbc存取session!");
        HttpSession session = request.getSession();
        // 看看session里面到底是什么
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            log.info(attributeNames.nextElement().toString());
        }

        String value = (String) session.getAttribute(name);
        return "sessionId:" + session.getId() + ", value:" + value;
    }
}