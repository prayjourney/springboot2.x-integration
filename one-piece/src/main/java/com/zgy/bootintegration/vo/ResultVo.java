package com.zgy.bootintegration.vo;

import lombok.ToString;

/**
 * 统一返回结果pojo
 */
@ToString
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

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
