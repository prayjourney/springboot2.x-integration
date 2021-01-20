package com.zgy.learn.bootshiro.config;

import com.zgy.learn.bootshiro.pojo.User;
import com.zgy.learn.bootshiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
 * @author zuiguangyin
 * @date 2020/11/5
 * @description 自定义的realme, 需要继承AuthorizingRealm, 此处加入Spring管理, 直接创建好了userRealm的bean
 */
@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    // 认证--->能不能登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 认证封装在了subject.login()之中, 直接在登录的Controller方法之中, 进行认证, 这个套路有点深, 封装了好多层。
        log.info("执行了认证！");

        /*
         * ①一个自己处理的例子, 输入root, 123456就正确
         * String username = "root";
         * String password = "123456";
         *
         * ②验证用户名
         * UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
         * if (!usernamePasswordToken.getUsername().equals(username)) {
         *     return null; // UnknownAccountException, 用户名错误
         * }
         *
         * ③验证密码，shiro帮我们做, 不用我们做。 // IncorrectCredentialsException, 密码错误
         * return new SimpleAuthenticationInfo("", password, "");
         *
         */

        // 连接真实的数据库, 用户名验证, 密码验证
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.queryUserByName(token.getUsername());
        if (null == user) {
            // 用户验证, 表示没有这个用户
            return null;
        }

        // 将用户信息分发给授权doGetAuthorizationInfo函数, 密码验证
        // return new SimpleAuthenticationInfo("", person.getPassword(), "");
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }


    // 授权--->去操作某一个东西
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行了授权！");

        /*
         * 一个自己处理的例子, 给进来的用户都加一个权限
         * SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
         * authorizationInfo.addStringPermission("user:add");
         * return authorizationInfo;
         *
         */
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 连接真实的数据库, 权限的验证, 拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        // 拿到当前对象, Principal可以是任何对象, 一般而言就是认证时候的对象, 登录后, 需要按照这个用户去授权, 等一些列的操作, 可以传递参数
        User currentUser = (User) subject.getPrincipal();

        // 设置当前对象的权限, shiro自己去比较, 剩下的不用我们管了
        authorizationInfo.addStringPermission(currentUser.getPerms());

        // 设置当前对象的角色
        authorizationInfo.addRole(currentUser.getRole());
        return authorizationInfo;
    }

}
