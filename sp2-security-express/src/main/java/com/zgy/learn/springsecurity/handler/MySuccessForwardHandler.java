package com.zgy.learn.springsecurity.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-02-22
 * @modified :
 */
public class MySuccessForwardHandler implements AuthenticationSuccessHandler {
    private String url;

    public MySuccessForwardHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 重定向到某个url
        response.sendRedirect(url);
    }
}
