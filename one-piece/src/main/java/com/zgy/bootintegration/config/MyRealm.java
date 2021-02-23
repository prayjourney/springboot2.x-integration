package com.zgy.bootintegration.config;

import com.zgy.bootintegration.pojo.User;
import com.zgy.bootintegration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

import java.time.Instant;

/**
 * @author: z.g.y
 * @description: 自定义的Realm, 继承自AuthorizingRealm
 * @date: Created in 2020/6/14 23:01
 * @modified:
 */
@Slf4j
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService service;

    // 第一步: 认证，登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("正在登陆的操作... 时间是: {}!", Instant.now().getNano());
        // 此处的authenticationToken是Controller传来之后，封装好，然后传到此处的
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 获取真实的数据， 连接数据库
        User user = service.queryUserByName(token.getUsername());

        // 没有此人
        if (null == user) {
            return null; // 抛出异常， unknownAccountException
        }
        // 判断用户名密码是否不为空
        if (null == user.getName() || StringUtils.isBlank(user.getName())) {
            log.error("用户名不能为空");
            return null;
        }
        if (null == user.getPassword() || StringUtils.isBlank(user.getPassword())) {
            log.error("密码不能为空");
            return null;
        }

        // 上述基本的检查okay，就把他封装了，相当于把User返回了，后面就可供给登陆使用
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }

    // 第二步: 授权，访问资源
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("正在授权的操作... 时间是: {}!", Instant.now().getNano());
        // 权限的控制
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // authorizationInfo.addStringPermission("user:add"); // 这个在实际业务之中，应该从数据库之中获取

        // 拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();

        // 设置当前用户的权限，数据库之中获取
        authorizationInfo.addStringPermission(currentUser.getPerms());
        // 角色信息的设置和获取
        authorizationInfo.addRole(currentUser.getRoles());

        return authorizationInfo;
    }


}
