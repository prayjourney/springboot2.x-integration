package com.zgy.learn.springsecurity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-02-21
 * @modified :
 */
@Service
@Slf4j
public class LoginService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public LoginService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("正在执行登录服务...");
        // 1. 根据用户名查找数据库, 不存在就抛异常, 添加了两个用户admin, zhangsan
        if (!"admin".equals(username) && !"zhangsan".equals(username)) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        if ("admin".equals(username)) {
            // 2. 比较密码(注册时保存到数据库之中的已经是加密过的密码)
            String password = passwordEncoder.encode("123456");
            return new User(username, password,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("admin, user, ROLE_admin, insert, delete, update, select"));
        } else if ("zhangsan".equals(username)) {
            // 2. 比较密码(注册时保存到数据库之中的已经是加密过的密码)
            String password = passwordEncoder.encode("123456");
            return new User(username, password,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("user, ROLE_vip, select"));
        } else {
            return new User("", "", AuthorityUtils.commaSeparatedStringToAuthorityList(""));
        }
    }
}
