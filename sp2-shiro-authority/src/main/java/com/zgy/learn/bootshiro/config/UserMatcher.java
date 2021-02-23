package com.zgy.learn.bootshiro.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;

/**
 * @author: pray-journey.io
 * @description: 密码匹配器
 * @date: created in 2021-01-26
 * @modified: https://www.jianshu.com/p/dd646345d830 之中的方法有问题, 还是需要继承HashedCredentialsMatcher
 * @probleam: 还有问题
 */
@Component
public class UserMatcher extends HashedCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenHashedCredentials = hashProvidedCredentials(token, info);
        Object accountCredentials = getCredentials(info);
        return super.equals(tokenHashedCredentials, accountCredentials);
    }

    @Override
    protected Object hashProvidedCredentials(AuthenticationToken token, AuthenticationInfo info) {
        Object salt = null;
        if (info instanceof SaltedAuthenticationInfo) {
            salt = ((SaltedAuthenticationInfo) info).getCredentialsSalt();
        } else {
            //retain 1.0 backwards compatibility:
            if (isHashSalted()) {
                salt = getSalt(token);
            }
        }
        return hashProvidedCredentials(token.getCredentials(), salt, getHashIterations());
    }

    @Override
    protected Hash hashProvidedCredentials(Object credentials, Object salt, int hashIterations) {
        return new SimpleHash("SHA-256", credentials, salt, hashIterations);
    }

    @Override
    protected Object getCredentials(AuthenticationInfo info) {
        Object credentials = info.getCredentials();

        byte[] storedBytes = toBytes(credentials);

        if (credentials instanceof String || credentials instanceof char[]) {
            //account.credentials were a char[] or String, so
            //we need to do text decoding first:
            if (isStoredCredentialsHexEncoded()) {
                storedBytes = Hex.decode(storedBytes);
            } else {
                storedBytes = Base64.decode(storedBytes);
            }
        }
        SimpleHash simpleHash = new SimpleHash("SHA-256");
        simpleHash.setBytes(storedBytes);
        return simpleHash;
    }
}
