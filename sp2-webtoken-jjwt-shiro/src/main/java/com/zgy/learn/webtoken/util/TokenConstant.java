package com.zgy.learn.webtoken.util;

import java.util.UUID;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-02-02
 * @modified:
 */
public interface TokenConstant {
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
