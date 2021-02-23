package com.zgy.learn.token.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 说明：BasicHttpAuthenticationFilter只是一个用来验证是否登录的拦截器
 *
 * @author: z.g.y
 * @date: 2021/2/4
 * @description: 重写Filter
 * 所有的请求都会先经过Filter, 所以我们继承官方的BasicHttpAuthenticationFilter, 并且重写鉴权的方法, 另外通过重写preHandle, 实现跨越访问
 * 代码的执行流程preHandle->isAccessAllowed->isLoginAttempt->executeLogin
 */
@Slf4j
@Component
public class JwtTokenFilter extends BasicHttpAuthenticationFilter {
    /**
     * 对跨域提供支持
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }


    /**
     * 判断用户是否想要登入, 检测header里面是否包含jwt-token字段或者Authorization字段即可, 这个是自己设置的
     * 如果是尝试登录, 返回true, 如果不是尝试登录, 返回false, 通过获取header之中的字段, 有jwt-token就是我们去做相关操作了, 而不是登录
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("jwt-token");
        if (null != authorization) {
            return false;
        } else {
            try {
                // 如果是尝试登陆, 跳转到登录页面
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }


    /**
     * 这个方法是对处理没有获得授权的请求
     * 如果是登陆的请求的我们直接放行, 也可以在配置文件中为登陆的url配置直接放行
     * 对于需要校验token的接口, 用subject login来校验是否能获取系统的权限
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 如果是尝试登陆, 跳转到登录页面
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect("/401");
        return true;
    }

}