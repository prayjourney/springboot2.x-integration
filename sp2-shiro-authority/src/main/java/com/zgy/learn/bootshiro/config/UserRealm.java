package com.zgy.learn.bootshiro.config;

import com.zgy.learn.bootshiro.nopassword.NoSecretToken;
import com.zgy.learn.bootshiro.pojo.User;
import com.zgy.learn.bootshiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: zuiguangyin
 * @date: 2020/11/5
 * @description: 自定义的realme, 需要继承AuthorizingRealm, 此处加入Spring管理, 直接创建好了userRealm的bean
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

        // 判断是否免密登陆类型
        if (authenticationToken instanceof NoSecretToken) {
            NoSecretToken token = (NoSecretToken) authenticationToken;
            String user = token.getUsername();
            return new SimpleAuthenticationInfo(user, "123456", user);
        } else {
            // 输入的信息, 用户名, 密码
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            String userName = token.getPrincipal().toString();
            String password = new String(token.getPassword());

            // 数据库中获取用户, null表示没有这个用户, salt, pwdInDB从数据库之中获取
            User user = userService.queryUserByName(token.getUsername());
            if (null == user) {
                return null;
            }
            String salt = user.getSalt();
            String pwdInDB = user.getPassword();

            // 使用数据库之中的信息构造, 第一个参数需要是User对象, 给授权使用
            // SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, pwdInDB, ByteSource.Util.bytes(salt), getName());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, pwdInDB, ByteSource.Util.bytes(salt), getName());
            return info;
        }
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

    // shiro配置自定义密码加密器: https://www.cnblogs.com/Sky0914/p/12561474.html
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher master = new HashedCredentialsMatcher();
        // 散列算法: 加密
        master.setHashAlgorithmName("SHA-256");
        // 散列的次数, 加密次数, 比如用md5散列两次, 相当于md5(md5("xxx"));
        master.setHashIterations(1024);

        // 这个语句一定要调用
        super.setCredentialsMatcher(master);

        // 下面这个反正是有问题, 还麻烦, 还不如上面的
        //// 自定义认证加密方式
        //UserMatcher userMatcher = new UserMatcher();
        //// 设置自定义认证加密方式
        //super.setCredentialsMatcher(userMatcher);
    }

}
