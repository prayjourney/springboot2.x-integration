package com.zgy.learn.token.shiro.filter;

import com.zgy.learn.token.shiro.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
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
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                response401(request, response);
            }
        }
        return true;
    }


    /**
     * 判断用户是否想要登入, 检测header里面是否包含jwt-token字段或者Authorization字段即可, 这个是自己设置的
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("jwt-token");
        return !(null == authorization);
    }


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 这个地方和前端约定, 要求前端将token放入到header部分, 以后的每次请求, 前端都需要在header之中放入Authorization的token
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 存放在header之中的名称, 需要前后端约定
        String token = httpRequest.getHeader("jwt-token");
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


    /**
     * 这个方法是对处理没有获得授权的请求
     * 如果是登陆的请求的我们直接放行，也可以在配置文件中为登陆的url配置直接放行
     * 对于需要校验token的接口，用subject login来校验是否能获取系统的权限
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (!isLoginAttempt(request, response)) {
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


    /**
     * 将非法请求跳转到401页面
     */
    private void response401(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/401");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}