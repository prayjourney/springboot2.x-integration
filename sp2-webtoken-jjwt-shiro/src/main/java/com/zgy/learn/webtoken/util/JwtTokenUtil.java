package com.zgy.learn.webtoken.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.learn.webtoken.pojo.OpUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-02-02
 * @modified :
 */
@Component
public class JwtTokenUtil {
    /**
     * 生成token
     *
     * @param opUser
     * @return
     * @throws JsonProcessingException
     */
    public String createToken(OpUser opUser) throws JsonProcessingException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        SecretKey key = generateKey();
        String tid = TokenConstant.JWT_ID;
        JwtBuilder builder = Jwts.builder();

        // 私有声明, 自定义的字段
        Map<String, Object> claims = new HashMap<>();
        opUser.setPassword("").setSalt("");
        claims.put("user", opUser);
        builder.setClaims(claims);
        // 官方字段
        builder.setId(tid).setIssuedAt(now).setIssuer("z.g.y").setSubject(opUser.getId().toString()).setAudience(opUser.getId().toString());
        // 设置过期时间, 生效时间
        long expMillis = nowMillis + TokenConstant.JWT_EXPIRED_TIME;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp).setNotBefore(now);

        // 签发
        builder.signWith(signatureAlgorithm, key);
        return builder.compact();
    }


    /**
     * 解析token内容
     *
     * @param jwt
     * @return
     */
    public Claims parseToken(String jwt) {
        SecretKey key = generateKey();
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        // 所有的内容, 都在claim之中, 官方的字段, 我们自己定义的字段
        // claims.getSubject();
        // claims.getExpiration();
        // claims.get("uid");
        return claims;
    }


    /**
     * 验证token的有效性
     *
     * @param jwt
     * @return
     */
    public boolean validToken(String jwt) {
        Date now = new Date();
        SecretKey key = generateKey();
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        Date expiration = claims.getExpiration();
        if (now.after(expiration)) {
            return false;
        }
        return true;
    }


    // 生成秘钥
    private SecretKey generateKey() {
        String stringKey = TokenConstant.JWT_SECRET;
        SecretKey key = new SecretKeySpec(stringKey.getBytes(), 0, stringKey.getBytes().length, "AES");
        return key;
    }
}
