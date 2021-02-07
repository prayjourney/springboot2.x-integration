package com.zgy.learn.webtoken.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-02-08
 * @modified :
 */
@Component
public class JwtCredentialsMatcher implements CredentialsMatcher {

    /**
     * 下面两个参数我就照抄文档了
     * 这个类的作用是比较两个
     *
     * @param token the {@code AuthenticationToken} submitted during the authentication attempt
     * @param info  the {@code AuthenticationInfo} stored in the system.
     * @return {@code true} if the provided token credentials match the stored account credentials,
     * {@code false} otherwise.
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return false;
    }
}
