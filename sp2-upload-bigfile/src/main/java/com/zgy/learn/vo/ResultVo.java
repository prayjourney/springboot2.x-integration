package com.zgy.learn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 返回结果包装对象
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class ResultVo<T> {

    private ResultStatus status;

    private String msg;

    private T data;

    public ResultVo(ResultStatus status) {
        this(status, status.getReasonPhrase(), null);
    }

    public ResultVo(ResultStatus status, T data) {
        this(status, status.getReasonPhrase(), data);
    }

    public ResultVo(ResultStatus status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
