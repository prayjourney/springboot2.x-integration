package com.zgy.learn.token.util.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: z.g.y
 * @date: 2021/2/7
 * @description: 结果统一处理
 */
@Getter
@Setter
public class Result<T> {
    public Status status;
    private T data;
    private String msg;

    public Result(Status status) {
        this.status = status;
    }

    public Result(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(Status status, String msg, T data) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }
}
