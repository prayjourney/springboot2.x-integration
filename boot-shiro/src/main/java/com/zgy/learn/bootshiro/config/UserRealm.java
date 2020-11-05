package com.zgy.learn.bootshiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * @Author zuiguangying
 * @Date 2020/11/5
 * @Description 自定义的realme, 需要继承AuthorizingRealm
 */
@Component // 此处加入Spring管理, 直接创建好了userRealm的bean
@Slf4j
public class UserRealm extends AuthorizingRealm {

    // 认证，看能不能登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("执行了认证！");
        return null;
    }

    // 授权, 去操作某一个东西
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行了授权！");
        return null;
    }

}
