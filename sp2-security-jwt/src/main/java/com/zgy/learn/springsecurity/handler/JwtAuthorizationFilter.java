package com.zgy.learn.springsecurity.handler;

import com.zgy.learn.springsecurity.constants.JwtConstants;
import com.zgy.learn.springsecurity.utils.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: pray-journey.io
 * @date: 2021/2/25
 * @description: 用户权限的检测拦截器, 可以继承GenericFilterBean, OncePerRequestFilter这两个是spring-web里面的拦截器,
 * 也可以使用BasicAuthenticationFilter, 这个是spring-security的拦截器, 本质一样, 都要重写doFilterInternal方法
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    /**
     * 所有需要验证的请求都会来到这个方法中, 此处提取出客户端传来的jwt-token, 并进行解析, 并查看用户角色等信息
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        /*
         * 请求头之中获取token, 使用Authorization作为key标识, 没有token就放行, 有如下配置,
         * http.formLogin().addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
         * 放行后, 就到了UsernamePasswordAuthenticationFilter->attemptAuthentication尝试登录
         */
        String token = request.getHeader(JwtConstants.TOKEN_HEADER.val());
        if (token == null || !token.startsWith(JwtConstants.TOKEN_PREFIX.val())) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }

        /*
         * 到这儿表示请求之中有token, 然后我们解析token, 使用jwt-token构造UsernamePasswordAuthenticationToken
         */
        String tokenValue = token.replace(JwtConstants.TOKEN_PREFIX.val(), "");
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = JwtTokenUtil.getAuthentication(tokenValue);
        } catch (JwtException e) {
            logger.error("Invalid jwt : " + e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }
}
