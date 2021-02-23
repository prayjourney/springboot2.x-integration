package com.zgy.bootintegration.utils;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: z.g.y
 * @description: Token的工具
 * @date: Created in 2020/8/9 12:17
 * @modified:
 */
@Component
public class JwtTokenUtil {
    // 获取token
    public static String getTokenKidId() {
        // 从 http 请求头中取出 token
        // String token = getRequest().getHeader("token");
        // String kidId = JWT.decode(token).getAudience().get(0);

        // 可以从header之中获取，也可以从cookie之中获取，要看你把token放到了哪儿
        Cookie[] cookies = getRequest().getCookies();
        String token = null;
        String kidId = null;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("token")) {
                token = cookies[i].getValue();
                kidId = JWT.decode(token).getAudience().get(0);
            }
        }
        return kidId;
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}