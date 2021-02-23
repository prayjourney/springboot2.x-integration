package com.zgy.learn.webtoken.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zgy.learn.webtoken.pojo.Kid;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zgy
 * @date: 2021/1/13
 * @description:
 */
@Service
public class TokenService {
    //设置过期时间
    private static final long EXPIRE_DATE = 30 * 60 * 100000;

    /**
     * 简单生成token
     *
     * @param kid
     * @return
     */
    public String createToken(Kid kid) {
        Date start = new Date();
        // 一小时有效时间
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(String.valueOf(kid.getId())).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(kid.getPassword()));
        return token;
    }


    /**
     * 完整生成token
     *
     * @param kid
     * @return
     */
    public String createTokenComplete(Kid kid) {
        String token = "";

        try {
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            // 秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(kid.getPassword());
            // 设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            // 携带username, password信息, 生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", kid.getUsername())
                    .withClaim("password", kid.getPassword()).withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }
}