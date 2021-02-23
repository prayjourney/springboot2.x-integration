package com.zgy.bootintegration.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zgy.bootintegration.pojo.Kid;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: z.g.y
 * @description: token下发
 * @date: Created in 2020/8/9 12:14
 * @modified:
 */
@Service
public class TokenService {

    // 设置过期时间
    private static final long EXPIRE_DATE = 30 * 60 * 100000;

    // 简单生成token
    public String createToken(Kid kid) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(kid.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(kid.getPassword()));
        return token;
    }


    // 完整生成token
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
            // 携带username，password信息，生成签名
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