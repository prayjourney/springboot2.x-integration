package com.zgy.bootintegration.handler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zgy.bootintegration.annotation.KidLoginToken;
import com.zgy.bootintegration.annotation.PassToken;
import com.zgy.bootintegration.pojo.Kid;
import com.zgy.bootintegration.service.KidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器
 * 配置拦截器去获取token并验证token，实现一个拦截器就需要实现HandlerInterceptor接口
 * 主要流程:
 * 1. 从http请求头中取出token，或者从cookie之中取出cookie，这个要看设置的情况
 * 2. 判断是否映射到方法
 * 3. 检查是否有passtoken注释，有则跳过认证
 * 4. 检查有没有需要用户登录的注解，有则需要取出并验证
 * 5. 认证通过则可以访问，不通过会报相关错误信息
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    KidService kidService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        // 当前将没有注解的也设置为跳过认证
        if (null == method.getAnnotation(PassToken.class)) {
            return true;
        }

        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(KidLoginToken.class)) {
            KidLoginToken kidLoginToken = method.getAnnotation(KidLoginToken.class);

            // 1. 如果token放到了header之中，则从http请求头中取出token
            // String token = httpServletRequest.getHeader("token");

            // 2.如果token放到了cookie之中，则从cookie之中获取
            Cookie[] cookies = httpServletRequest.getCookies();
            String token = null;
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("token")) {
                    token = cookies[i].getValue();
                }
            }

            if (kidLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 kid id
                Integer kidId;
                try {
                    kidId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                Kid kid = kidService.findKidById(kidId);
                if (null == kid) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(kid.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}