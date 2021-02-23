package com.zgy.learn.token.util.result;

/**
 * @author: z.g.y
 * @date: 2021/2/7
 * @description: 结果统一处理, 状态信息
 */
public enum Status {
    OKAY(200, "操作成功!"),
    ERROR(400, "操作失败!");

    private int value;
    private String description;

    Status(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
