package com.ffzx.ffsip.wechat.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/7.
 */
public class Token {

    private String token;
    private Date createTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
}
