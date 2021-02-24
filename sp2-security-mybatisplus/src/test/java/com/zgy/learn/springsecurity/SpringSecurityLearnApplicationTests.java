package com.zgy.learn.springsecurity;

import com.zgy.learn.springsecurity.pojo.SecurityUser;
import com.zgy.learn.springsecurity.service.SecurityUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootTest
class SpringSecurityLearnApplicationTests {
    @Autowired
    SecurityUserService securityUserService;

    @Test
    void contextLoads() {
    }

    @Test
    public void selectUser() {
        int id = 1;
        SecurityUser securityUser = securityUserService.getById(id);
        System.out.println(securityUser);
    }

    @Test
    public void createAdminUser() {
        String username = "admin";
        String password = "123456";
        String roles = "ROLE_admin";
        String authorities = "admin,user,insert,delete,update,select";

        // 密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptPassword = encoder.encode(password);
        Date now = new Date();
        SecurityUser securityUser =
                new SecurityUser().setUsername(username).setPassword(encryptPassword)
                        .setRoles(roles).setAuthorities(authorities).setCreateTime(now).setUpdateTime(now);
        securityUserService.insertUser(securityUser);
    }

    @Test
    public void createVipUser() {
        String username = "zhangsan";
        String password = "123456";
        String roles = "ROLE_vip";
        String authorities = "user,select";

        // 密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptPassword = encoder.encode(password);
        Date now = new Date();
        SecurityUser securityUser =
                new SecurityUser().setUsername(username).setPassword(encryptPassword)
                        .setRoles(roles).setAuthorities(authorities).setCreateTime(now).setUpdateTime(now);
        securityUserService.insertUser(securityUser);
    }

    /**
     * 下面两个都是true, 那么BCryptPasswordEncoder安全吗? 看起来这样的话, 加密就不需在用户之中保存盐了
     * 这就需要了解BCryptPasswordEncoder加密解密的原理
     */
    @Test
    public void testBCryptPasswordEncoder01() {
        // 加密后的密码从数据表之中来, 解密
        String pwdInDB = "$2a$10$oE4WtauORnoRwoA0tOjuaOQcEEDbcJvRMU/MYwmcs2ZfRoWWsZjge";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean res = encoder.matches("123456", pwdInDB);
        System.out.println(res);
    }

    @Test
    public void testBCryptPasswordEncoder02() {
        // 加密后的密码从数据表之中来, 解密
        String pwdInDB = "$2a$10$a.tDz8cPkDTM7Q1ysktaq.ASRdWrbbzx/9VDgsKGVy6/va6Rwn4Wm";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean res = encoder.matches("123456", pwdInDB);
        System.out.println(res);
    }

}
