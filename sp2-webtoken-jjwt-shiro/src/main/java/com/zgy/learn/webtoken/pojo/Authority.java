package com.zgy.learn.webtoken.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * (Authority)实体类
 *
 * @author makejava
 * @since 2021-02-01 00:55:19
 */
public class Authority implements Serializable {
    private static final long serialVersionUID = -25113295564521007L;
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 显示名称
     */
    private String displayName;
    /**
     * 建立时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}