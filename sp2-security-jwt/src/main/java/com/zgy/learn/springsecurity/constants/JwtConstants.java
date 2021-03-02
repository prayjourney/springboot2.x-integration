package com.zgy.learn.springsecurity.constants;

/**
 * @author: pray-journey.io
 * @date: 2021/2/25
 * @description: jwt相关的常量
 */
public enum JwtConstants {
    // JwtToken的安全秘钥
    JWT_SECRET_KEY("HelloWorld!!!=SLP&*%jh@@^12sd+&MKD#))*SPW8"),
    TOKEN_HEADER("Authorization"),
    TOKEN_PREFIX("Bearer "),
    TOKEN_TYPE("JWT"),
    // 一小时毫秒数
    TOKEN_EXPIRE_TIME("3600000");

    private String values;

    JwtConstants(String values) {
        this.values = values;
    }

    public String val() {
        return this.values;
    }

}
