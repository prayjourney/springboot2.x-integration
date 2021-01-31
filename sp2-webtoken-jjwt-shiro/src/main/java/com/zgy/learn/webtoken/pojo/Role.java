package com.zgy.learn.webtoken.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2021-02-01 00:56:51
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -14076222853162143L;
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 角色名称
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