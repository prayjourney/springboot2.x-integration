package com.zgy.learn.webtoken.handler;

import com.zgy.learn.webtoken.config.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author renjiaxin
 * @date 2021/2/4
 */

@Slf4j
@Component
public class JwtTokenFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.warn("isAccessAllowed方法被調用...");
        // 这里先让它始终返回false, 然后来使用onAccessDenied(), 在onAccessDenied方法之中我们写自己的逻辑
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 只有在debug情况下才会输出日志
        if (log.isDebugEnabled()) {
            log.debug("onAccessDenied方法被調用, 访问的URI: {}", ((HttpServletRequest) request).getRequestURI());
        }

        // 这个地方和前端约定, 要求前端将token放入到header部分, 以后的每次请求, 前端都需要在header之中放入Authorization的token
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 存放在header之中的名称, 需要前后端约定
        String token = httpRequest.getHeader("zgy-token");
        if (StringUtils.isEmpty(token)) {
            // 向客户端返回错误信息
            onLoginFail(response);
            return false;
        }

        // 封装token, 然后登陆
        log.info("header之中的token:{}", token);
        JwtToken jwtToken = new JwtToken(token);
        // 固定写法
        try {
            // 委托realm进行登录认证, 所以此处还是最终使用JwtRealm进行登录认证, 也就是subject.login(token)
            getSubject(request, response).login(jwtToken);
        } catch (Exception e) {
            log.error(e.getMessage());
            onLoginFail(response);
            return false;
        }
        // 执行之中没有抛出异常就表示登录成功
        return true;
    }

    // 登录失败时候返回 401 状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }
}