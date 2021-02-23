package com.zgy.learn.token.util;

import java.util.UUID;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-02-02
 * @modified:
 */
public interface TokenConstant {
    /**
     * 专门为登录而设置的一个Token的id
     */
    String JWT_LOGIN_ID = "0xMG1220-=er3rt!!!+=0-345*&3456e&ER*(";
    /**
     * Token的唯一id
     */
    String JWT_ID = UUID.randomUUID().toString();

    /**
     * 原始的秘钥
     */
    String JWT_SECRET = "abc_helloWorld!!!++==!!!pp12";

    /**
     * 过期时间30分钟
     */
    Integer JWT_EXPIRED_TIME = 30 * 60 * 1000;
}
