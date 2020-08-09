package com.zgy.bootintegration.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zgy.bootintegration.pojo.Kid;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: renjiaxin
 * @Despcription: token 下发
 * @Date: Created in 2020/8/9 12:14
 * @Modified by:
 */
@Service
public class TokenService {

    public String getToken(Kid kid) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(kid.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(kid.getPassword()));
        return token;
    }
}