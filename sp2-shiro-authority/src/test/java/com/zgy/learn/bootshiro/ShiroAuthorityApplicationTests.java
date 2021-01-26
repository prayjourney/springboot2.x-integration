package com.zgy.learn.bootshiro;


import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

}
