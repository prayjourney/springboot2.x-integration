package com.zgy.learn.springsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.springsecurity.mapper.SecurityUserMapper;
import com.zgy.learn.springsecurity.pojo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: pray-journey.io
 * @despcription: 自定义的登录逻辑服务
 * @date: created in 2021-02-21
 * @modified :
 */
@Service
@Slf4j
public class MyAuthenticationService implements UserDetailsService {

    private SecurityUserMapper securityUserMapper;

    public MyAuthenticationService(SecurityUserMapper securityUserMapper) {
        this.securityUserMapper = securityUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("正在执行登录服务...");
        /**
             // 下面是模拟生成对象
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
         */
        // 使用真实的数据库, 获取用户的信息
        QueryWrapper<SecurityUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        SecurityUser securityUser = securityUserMapper.selectOne(queryWrapper);
        if (null == securityUser) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 存储在数据库之中的密码
        String passwordInDB = securityUser.getPassword();
        // 获取用户权限
        String authorities = securityUser.getAuthorities();
        // 获取用户角色(直接已经设置为ROLE_XXX这种格式了)
        String roles = securityUser.getRoles();
        String allAuthorityInfos = authorities + "," + roles;
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(allAuthorityInfos);

        // 把账号是否可用, 账号是否过期, 凭证(密码)是否失效, 账户是否锁定等信息在用户信息之中存储, 这样更好控制
        // 此处使用的是org.springframework.security.core.userdetails.User, SecurityUser没有继承是因为这样更清爽一些
        return new User(username, passwordInDB, securityUser.isEnabled(), securityUser.isAccountNonExpired(),
                securityUser.isCredentialsNonExpired(), securityUser.isAccountNonLocked(), grantedAuthorities);
    }
}
