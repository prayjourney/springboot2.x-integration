package com.zgy.learn.webtoken.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public Claims parseToken(String jwt) {
        SecretKey key = generateKey();
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        return claims;
    }


    private SecretKey generateKey() {
        String stringKey = TokenConstant.JWT_SECRET;
        SecretKey key = new SecretKeySpec(stringKey.getBytes(), 0, stringKey.getBytes().length, "AES");
        return key;
    }
}
