package com.zgy.learn.springsecurity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author: pray-journey.io
 * @date: 2021/2/22
 */
@Service
@Slf4j
public class MyAccessService {
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 获取主体
        Object obj = authentication.getPrincipal();
        if (obj instanceof UserDetails) {
            // 获取权限
            UserDetails userDetails = (UserDetails) obj;
            Collection<? extends GrantedAuthority> authorities =
                    userDetails.getAuthorities();
            log.info(String.valueOf(authorities));
            // 判断主体的权限里面是否包含请求, 第一个是url, 一般不这样做, 还是和具体的权限或者角色联系起来
            // return authorities.contains(new SimpleGrantedAuthority(request.getRequestURI()));
            return authorities.contains(new SimpleGrantedAuthority("admin"));
        }
        return false;
    }

}
