package com.zgy.bootintegration.utils;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/8/9 12:17
 * @Modified by:
 */
@Component
public class JwtTokenUtil {

    public static String getTokenUserId() {
        // 从 http 请求头中取出 token
        String token = getRequest().getHeader("token");
        String kidId = JWT.decode(token).getAudience().get(0);
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