package com.zgy.learn.webtoken.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.learn.webtoken.annotation.NeedLogin;
import com.zgy.learn.webtoken.annotation.PassLogin;
import com.zgy.learn.webtoken.pojo.Kid;
import com.zgy.learn.webtoken.service.KidService;
import com.zgy.learn.webtoken.service.TokenJjwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-18
 * @modified:
 */
@Component
public class LoginTokenInterceptor implements HandlerInterceptor {
    @Autowired
    KidService kidService;

    @Autowired
    TokenJjwtService tokenJjwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // response.setHeader("Access-Control-Allow-Origin", "*");
        // response.setHeader("Access-Control-Allow-Credentials", "true");
        // response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        // response.setHeader("Access-Control-Max-Age", "86400");
        // response.setHeader("Access-Control-Allow-Headers", "Authorization");

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 当前将没有注解的也设置为跳过认证
        if (null == method.getAnnotation(PassLogin.class)) {
            return true;
        }

        // 检查是否有PassLogin注释, 有则跳过认证
        if (method.isAnnotationPresent(PassLogin.class)) {
            PassLogin passToken = method.getAnnotation(PassLogin.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(NeedLogin.class)) {
            NeedLogin needLogin = method.getAnnotation(NeedLogin.class);

            // 写入到了cookie
            Cookie[] cookies = request.getCookies();
            String token = null;
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("jwtToken")) {
                    token = cookies[i].getValue();
                }
            }

            if (needLogin.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }

                // 获取token中的kidId
                Claims claims = tokenJjwtService.parseJWT(token);
                String subject = claims.getSubject();
                ObjectMapper objectMapper = new ObjectMapper();
                HashMap<String, Object> map = objectMapper.readValue(subject, new TypeReference<HashMap<String, Object>>() {
                });
                Integer kidId = Integer.valueOf(map.get("id").toString());

                // 简单验证
                Kid kid = kidService.findKidById(kidId);
                if (null == kid) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                return true;
            }
        }

        return true;
    }
}
