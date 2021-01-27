package com.zgy.learn.bootshiro;


import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ShiroAuthorityApplicationTests {

    @Test
    void contextLoads() {
    }

    // MD5不可逆加密
    @Test
    public void testMd5Encrypt() {
        String password = "123";
        String encodedPassword = new Md5Hash(password).toString();
        System.out.println("原始密码: " + password + ", md5加密后的密码: " + encodedPassword);
    }

    // sha256不可逆加密
    @Test
    public void testSha256Encrypt() {
        String password = "123";
        String encodedPassword = new Sha256Hash(password).toString();
        System.out.println("原始密码: " + password + ", Sha256加密后的密码: " + encodedPassword);
    }

    // sha256+salt盐值加密
    @Test
    public void testSha256WithSaltEncrypt01() {
        String password = "123";
        String salt = "hello***World!!!";
        String encodedPassword = new SimpleHash("SHA-256", password, salt).toString();

        System.out.printf("原始密码是: %s, 盐是: %s, 运算出来的密文是: %s", password, salt, encodedPassword);
    }

    // sha256+salt盐值+hash次数加密
    @Test
    public void testSha256WithSaltEncrypt02() {
        String password = "123";
        String salt = "hello***World!!!";
        String encodedPassword = new SimpleHash("SHA-256", password, salt, 1024).toString();

        System.out.printf("原始密码是: %s, 盐是: %s, 运算出来的密文是: %s", password, salt, encodedPassword);
    }

    // uuid
    @Test
    public void testUUID() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

}
