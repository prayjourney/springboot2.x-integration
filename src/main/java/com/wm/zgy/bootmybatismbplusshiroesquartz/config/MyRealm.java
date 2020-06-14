package com.wm.zgy.bootmybatismbplusshiroesquartz.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * @Author: renjiaxin
 * @Despcription: 自定義的Realm, 继承自AuthorizingRealm
 * @Date: Created in 2020/6/14 23:01
 * @Modified by:
 */
@Component
public class MyRealm extends AuthorizingRealm {
    // 认证，登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("登录！");
        return null;
    }

    // 授权，访问资源
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权！");
        return null;
    }


}
