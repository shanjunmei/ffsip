package com.ffzx.ffsip.model;

import com.ffzx.orm.common.BaseEntity;
import javax.persistence.*;

@Table(name = "user")
public class User extends BaseEntity {
    /**
     * 密码
     */
    private String password;

    private String name;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取邮件地址
     *
     * @return email - 邮件地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮件地址
     *
     * @param email 邮件地址
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}