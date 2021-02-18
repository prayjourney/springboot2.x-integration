package com.zgy.learn.token.shiro.filter;

import com.zgy.learn.token.shiro.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author z.g.y
 * @date 2021/2/4
 */

@Slf4j
@Component
public class JwtTokenFilter extends BasicHttpAuthenticationFilter {

    /**
     * 检查是否登陆请求
     */
    private boolean isLoginAttempt(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        return "/login".equals(servletPath);
    }


    /**
     * 这个方法是对处理没有获得授权的请求
     * 如果是登陆的请求的我们直接放行，也可以在配置文件中为登陆的url配置直接放行
     * 对于需要校验token的接口，用subject login来校验是否能获取系统的权限
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (!isLoginAttempt(request)) {
            // 这个地方和前端约定, 要求前端将token放入到header部分, 以后的每次请求, 前端都需要在header之中放入Authorization的token
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            // 存放在header之中的名称, 需要前后端约定
            String token = httpRequest.getHeader("jwt-token");

            if (StringUtils.isEmpty(token)) {
                return false;
            }
            JwtToken jwtToken = new JwtToken(token);
            getSubject(request, response).login(jwtToken);
        }

        // 执行之中没有抛出异常就表示登录成功
        return true;
    }

}