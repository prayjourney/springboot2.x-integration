package com.zgy.learn.webtoken.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * OpUser实体类
 *
 * @author z.g.y
 * @since 2021-02-01 00:57:54
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OpUser implements Serializable {
    private static final long serialVersionUID = 914918679313307010L;
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * Email
     */
    private String email;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 账号失效
     */
    private Object accountDisabled;
    /**
     * 账号过期
     */
    private Object accountExpired;
    /**
     * 账号冻结
     */
    private Object accountLocked;
    /**
     * 密码过期
     */
    private Object credentialsExpired;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Object getAccountDisabled() {
        return accountDisabled;
    }

    public void setAccountDisabled(Object accountDisabled) {
        this.accountDisabled = accountDisabled;
    }

    public Object getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Object accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Object getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Object accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Object getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Object credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
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