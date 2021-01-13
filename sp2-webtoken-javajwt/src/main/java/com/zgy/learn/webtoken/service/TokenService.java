package com.zgy.learn.webtoken.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zgy.learn.webtoken.pojo.Kid;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zgy
 * @date 2021/1/13
 * @description
 */
@Service
public class TokenService {

    public String getToken(Kid kid) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(String.valueOf(kid.getId())).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(kid.getPassword()));
        return token;
    }
}