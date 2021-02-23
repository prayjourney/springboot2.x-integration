package com.zgy.learn.richtextwangeditor.util;

import java.util.Arrays;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020-11-25 上午 02:17
 * @modified:
 */
public class ImgInfo {

    private Integer error;
    private String[] url;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImgInfo [error=" + error + ", url=" + Arrays.toString(url) + "]";
    }

}
