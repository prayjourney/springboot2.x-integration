package com.zgy.bootintegration.utils;

/**
 * bigfile大文件上传的常量枚举类
 */
public enum FileUploadConstants {
    // 异常信息统一头信息
    EXCEPTION_HEAD("程序发生异常, 请检查！"),
    // 保存文件所在路径的key
    FILE_MD5_KEY("FILE_MD5:"),
    // 保存上传文件的状态
    FILE_UPLOAD_STATUS("FILE_UPLOAD_STATUS");

    private String name;

    FileUploadConstants() {

    }

    FileUploadConstants(String name) {
        this.name = name;
    }

    public String val() {
        return name;
    }
}

