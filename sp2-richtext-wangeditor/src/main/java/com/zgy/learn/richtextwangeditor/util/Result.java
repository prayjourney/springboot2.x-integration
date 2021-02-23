package com.zgy.learn.richtextwangeditor.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author: z.g.y
 * @description: 返回统一结果
 * @date: Created in 2020-11-25 上午 12:45
 * @modified:
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Result<T> {
    /**
     * 100是正常结果
     */
    private Integer code;

    private String message;

    /**
     * 返回的数据
     */
    private T data;

    public Result() {

    }

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
