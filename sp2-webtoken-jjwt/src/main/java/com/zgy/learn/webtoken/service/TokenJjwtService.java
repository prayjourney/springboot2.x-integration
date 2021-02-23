package com.zgy.learn.webtoken.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.learn.webtoken.pojo.Kid;
import com.zgy.learn.webtoken.util.JjwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: pray-journey.io
 * @description: jwt token的服务
 * @date: created in 2021-01-18
 * @modified:
 */
@Service
public class TokenJjwtService {
    /**
     * 生成jwt
     *
     * @param id
     * @param kid
     * @param ttlMillis
     * @return
     * @throws JsonProcessingException
     */
    public String createJWT(String id, Kid kid, long ttlMillis) throws JsonProcessingException {
        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        /*
         * 如果有私有声明，一定要先设置这个自己创建的私有的声明，给builder的claim赋值，一旦写在标准的声明赋值之后，会覆盖了标准的声明
         * 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
         * claims和subject使用了相同的信息，一般而言使用标准的声明就够用了
         */
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", kid.getId());
        claims.put("userName", kid.getUsername());

        // 生成签名的时候使用的秘钥secret，这个秘钥不能泄漏，它就是服务端的私钥，在任何场景都不应该流露出去, 如果泄漏, 任何人都可以签发jwt了。
        SecretKey key = generateKey();

        // 下面就是在为payload添加各种标准声明和私有声明了, 这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder();
        builder.setClaims(claims);

        // jwt的id, 是JWT的唯一标识, jwt的签发时间, jwt签发人,
        builder.setId(id).setIssuedAt(now).setIssuer("z.g.y");
        // jwt的主体, 是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。设置签名的签名算法和签名使用的秘钥
        ObjectMapper objectMapper = new ObjectMapper();
        String subject = objectMapper.writeValueAsString(claims);
        builder.setSubject(subject).signWith(signatureAlgorithm, key);

        // 设置过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }


    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     */
    public Claims parseJWT(String jwt) {
        SecretKey key = generateKey();
        // 得到DefaultJwtParser, 设置签名的秘钥, 获取加密的claims之中带的信息
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        return claims;
    }


    // 由给定的字符串生成对称的秘钥, 对称与否根据自己需求, 下面使用的是AES对称加密, 使用给定的JWT_SECRET生成一个对称秘钥
    private SecretKey generateKey() {
        String stringKey = JjwtConstant.JWT_SECRET;
        SecretKey key = new SecretKeySpec(stringKey.getBytes(), 0, stringKey.getBytes().length, "AES");
        return key;
    }

}
