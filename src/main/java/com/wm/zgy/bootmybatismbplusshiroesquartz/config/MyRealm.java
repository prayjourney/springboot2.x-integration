package com.wm.zgy.bootmybatismbplusshiroesquartz.config;

import com.wm.zgy.bootmybatismbplusshiroesquartz.pojo.User;
import com.wm.zgy.bootmybatismbplusshiroesquartz.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: renjiaxin
 * @Despcription: 自定義的Realm, 继承自AuthorizingRealm
 * @Date: Created in 2020/6/14 23:01
 * @Modified by:
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService service;

    // 第一步: 认证，登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("登录！");

        // 用户名密码， 这个本来是要在数据库之中获取，此处测试
        // String name= "root";
        // String password= "123456";

        UsernamePasswordToken token  = (UsernamePasswordToken) authenticationToken;
        // 获取真实的数据， 连接数据库
        User user = service.queryUserByName(token.getUsername());

        // 没有此人
        if (null == user){
            return null;
        }
        /**
        if (!token.getUsername().equals(this.name)){
            return null; // 抛出异常， unknownAccountException
        }
        */

        // 密码认证， 相当于把User返回了
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }

    // 第二步: 授权，访问资源
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权！");
        // 权限的控制
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // authorizationInfo.addStringPermission("user:add"); // 这个在实际业务之中，应该从数据库之中获取

        // 拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User)subject.getPrincipal();

        // 设置当前用户的权限，数据库之中获取
        authorizationInfo.addStringPermission(currentUser.getPerms());
        // 角色信息的设置和获取
        authorizationInfo.addRole(currentUser.getRoles());

        return authorizationInfo;
    }


}
