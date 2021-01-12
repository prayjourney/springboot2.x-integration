package com.zgy.learn.webtoken.util;

import java.util.UUID;

/**
 * @author: pray-journey.io
 * @despcription:
 * @date: created in 2021-01-12
 * @modified :
 */
public interface JjwtConstant {
    String JWT_ID = UUID.randomUUID().toString();
    /**
     * 加密密文, 这已经是一个base64转码的了
     */
    // String JWT_SECRET = "abc_helloWorld!!!++==!!!pp12";
    String JWT_SECRET = "YWJjX2hlbGxvV29ybGQhISErKz09ISEhcHAxMg==";
    int JWT_TTL = 60 * 60 * 1000;
}
