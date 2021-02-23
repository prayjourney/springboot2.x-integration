package com.zgy.learn.webtoken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.learn.webtoken.pojo.Student;
import com.zgy.learn.webtoken.util.JjwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: pray-journey.io
 * @description: 使用student作为对象, 但是不是数据库ourschool之中的student对象
 * @date: created in 2021-01-13
 * @modified:
 */
public class SimpleJjwtTest {
    public static void main(String[] args) throws JsonProcessingException {
        SimpleJjwtTest test = new SimpleJjwtTest();
        Student student = new Student(1, "张三", 22);
        ObjectMapper objectMapper = new ObjectMapper();
        String subject = objectMapper.writeValueAsString(student);

        try {
            System.out.println("=================生成jwt=================");
            String jwt = test.createJWT(JjwtConstant.JWT_ID, "z.g.y", subject, JjwtConstant.JWT_TTL);
            System.out.println("JWT：" + jwt);

            System.out.println("=================解密jwt=================");
            Claims c = test.parseJWT(jwt);
            System.out.println(c.getId());
            System.out.println(c.getIssuedAt());
            System.out.println(c.getSubject());
            System.out.println(c.getIssuer());
            System.out.println(c.get("uid", String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 由给定的字符串生成对称的秘钥, 对称与否根据自己需求
     *
     * @return
     */
    public SecretKey generalKey() {
        String stringKey = JjwtConstant.JWT_SECRET;
        // 使用AES对称加密, 使用给定的JWT_SECRET生成一个对称秘钥
        SecretKey key = new SecretKeySpec(stringKey.getBytes(), 0, stringKey.getBytes().length, "AES");
        return key;
    }

    /**
     * 创建jwt
     *
     * @param id
     * @param issuer
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public String createJWT(String id, String issuer, String subject, long ttlMillis) {
        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", "123456");
        claims.put("user_name", "admin");
        claims.put("nick_name", "X-rapido");

        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
        // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = generalKey();

        // 下面就是在为payload添加各种标准声明和私有声明了, 这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder();
        // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
        builder.setClaims(claims);
        // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
        builder.setId(id);
        // issueat: jwt的签发时间
        builder.setIssuedAt(now);
        // issuer：jwt签发人
        builder.setIssuer(issuer);
        // sub(Subject)：代表这个JWT的主体，它的所有人，是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
        builder.setSubject(subject);
        // 设置签名使用的签名算法和签名使用的秘钥
        builder.signWith(signatureAlgorithm, key);

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
     * @throws Exception
     */
    public Claims parseJWT(String jwt) {
        // 签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        // 得到DefaultJwtParser, 设置签名的秘钥, 设置需要解析的jwt
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        return claims;
    }
}
