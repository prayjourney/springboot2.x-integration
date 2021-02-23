package com.zgy.learn.bootshiro.nopassword;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Component;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-20
 * @modified:
 */
@Setter
@Getter
@ToString
@Component
public class NoSecretToken extends UsernamePasswordToken {
    private String username;
    private String type;


    public NoSecretToken() {

    }

    public NoSecretToken(String username, String type) {
        this.username = username;
        this.type = type;
    }


    @Override
    public Object getCredentials() {
        // 这里和下方的123456对应，也可以别的，一致即可。
        return "123456";
    }
}
