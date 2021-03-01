package com.zgy.learn.springsecurity.config;

import com.zgy.learn.springsecurity.utils.JwtTokenUtil;
import com.zgy.learn.springsecurity.utils.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: pray-journey.io
 * @date: 2021/2/26
 * @despcription: 配置Security之中要使用的filter的Config, 提供各种filter的bean
 */
@Configuration
public class SecurityHandlerConfig {
    /**
     * 登陆成功，返回Token
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                List<String> authoritiesList = authorities.stream().map(authority -> authority.toString()).
                        collect(Collectors.toList());

                // 创建token
                String jwtToken = JwtTokenUtil.createToken(authentication.getName(), authoritiesList);
                ResponseUtil.responseJson(response, HttpStatus.OK.value(), jwtToken);
            }
        };
    }

    /**
     * 登陆失败
     */
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            String msg = null;
            if (exception instanceof BadCredentialsException) {
                msg = "密码错误";
            } else {
                msg = exception.getMessage();
            }
            ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), msg);
        };
    }

    /**
     * 未登录，返回401
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) ->
                ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), "请先登录");
    }

}
