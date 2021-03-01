package com.zgy.learn.springsecurity.utils;

import com.zgy.learn.springsecurity.constants.JwtConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: pray-journey.io
 * @date: 2021/2/25
 */
public class JwtTokenUtil {

    public static String createToken(String username, List<String> authorities) {
        String JWT_ID = UUID.randomUUID().toString();
        final Date createdTime = new Date();
        final Date expirationTime =
                new Date(createdTime.getTime() + Integer.parseInt(JwtConstants.TOKEN_EXPIRE_TIME.val()));
        String tokenPrefix = Jwts.builder()
                .setHeaderParam("type", JwtConstants.TOKEN_TYPE.val())
                .signWith(SignatureAlgorithm.HS256, JwtConstants.JWT_SECRET_KEY.val())
                .claim("auth", authorities)
                .setId(JWT_ID)
                .setIssuer("z.g.y")
                .setIssuedAt(createdTime)
                .setSubject(username)
                .setExpiration(expirationTime)
                .compact();
        // 添加token前缀"Bearer ";
        return JwtConstants.TOKEN_PREFIX.val() + tokenPrefix;
    }


    public static String getId(String token) {
        Claims claims = getClaims(token);
        return claims.getId();
    }


    // 使用jwt-token构造UsernamePasswordAuthenticationToken, 获取token之中的权限
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = getClaims(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        String userName = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(userName, token, authorities);
    }


    private static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JwtConstants.JWT_SECRET_KEY.val())
                .parseClaimsJws(token)
                .getBody();
    }


    private static List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        List<String> authList = (List<String>) claims.get("auth");
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (null != authList) {
            for (String auth : authList) {
                grantedAuthorities.add(new SimpleGrantedAuthority(auth));
            }
            return grantedAuthorities;
        }
        return grantedAuthorities;
    }

}
