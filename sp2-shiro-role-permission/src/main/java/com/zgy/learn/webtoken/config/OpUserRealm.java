package com.zgy.learn.webtoken.config;

import com.zgy.learn.webtoken.pojo.OpUser;
import com.zgy.learn.webtoken.pojo.RoleAuthority;
import com.zgy.learn.webtoken.pojo.UserRole;
import com.zgy.learn.webtoken.service.AuthorityService;
import com.zgy.learn.webtoken.service.OpUserService;
import com.zgy.learn.webtoken.service.RoleAuthorityService;
import com.zgy.learn.webtoken.service.RoleService;
import com.zgy.learn.webtoken.service.UserRoleService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: z.g.y
 * @date: 2021/2/1
 */
@Slf4j
@Component
public class OpUserRealm extends AuthorizingRealm {
    @Autowired
    OpUserService opUserService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleAuthorityService roleAuthorityService;

    @Autowired
    AuthorityService authorityService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始执行认证!");
        // 输入的信息, 用户名, 密码
        UsernamePasswordToken tk = (UsernamePasswordToken) token;
        String userName = tk.getPrincipal().toString();

        // 数据库中获取用户, null表示没有这个用户, salt, pwdInDB从数据库之中获取
        OpUser user = opUserService.queryByName(userName);
        if (null == user) {
            return null;
        }
        String salt = user.getSalt();
        String pwdInDB = user.getPassword();

        // 构造认证信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, pwdInDB, ByteSource.Util.bytes(salt), getName());
        return info;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("开始执行授权!");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 获取当前对象
        Subject subject = SecurityUtils.getSubject();
        OpUser currentUser = (OpUser) subject.getPrincipal();

        // 设置权限和角色, 用户->角色->权限, 用户->角色(多), 角色->权限(多)
        List<UserRole> userRoles = userRoleService.queryAllById(currentUser.getId());
        List<String> userRolesNames = userRoles.stream().map(userRole -> {
            Integer roleId = userRole.getRoleId();
            return roleService.queryById(roleId).getName();
        }).collect(Collectors.toList());

        // 权限的id
        List<List<Integer>> authoritiesListIds = userRoles.stream().map(userRole -> {
            Integer roleId = userRole.getRoleId();
            List<RoleAuthority> roleAuthorities = roleAuthorityService.queryAllById(roleId);
            List<Integer> roleAuthoritiesIds = roleAuthorities.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
            return roleAuthoritiesIds;
        }).collect(Collectors.toList());

        List<Integer> authoritiesIds = new ArrayList<>();
        for (List<Integer> ls : authoritiesListIds) {
            authoritiesIds.addAll(ls);
        }

        // 去重, 然后获取权限名
        List<String> userRolesAuthorities = authoritiesIds.stream().distinct().map(authorityId -> {
            return authorityService.queryById(authorityId).getName();
        }).collect(Collectors.toList());

        authorizationInfo.addRoles(userRolesNames);
        authorizationInfo.addStringPermissions(userRolesAuthorities);
        return authorizationInfo;
    }


    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        // 加密算法, 加密次数
        HashedCredentialsMatcher master = new HashedCredentialsMatcher();
        master.setHashAlgorithmName("SHA-256");
        master.setHashIterations(1024);
        // 设置密码匹配器
        super.setCredentialsMatcher(master);
    }

}
