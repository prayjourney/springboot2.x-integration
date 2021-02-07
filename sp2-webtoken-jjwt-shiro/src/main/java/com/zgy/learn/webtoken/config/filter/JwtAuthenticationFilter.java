package com.zgy.learn.webtoken.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author z.g.y
 * @date 2021/2/7
 * @description JwtToken的授权拦截器
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends BasicHttpAuthenticationFilter {
    @Override
    public void doFilterInternal(ServletRequest request,
                                 ServletResponse response,
                                 FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String headerToken = httpRequest.getHeader("jwt-token");

        // 如果没有带token
        if (null == headerToken) {
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}
