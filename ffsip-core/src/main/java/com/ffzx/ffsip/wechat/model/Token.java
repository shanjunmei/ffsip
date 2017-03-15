package com.ffzx.ffsip.wechat.model;

import com.ffzx.ffsip.util.DateUtil;

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

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", createTime=" + (createTime!=null?DateUtil.format(createTime,"yyyy-MM-dd HH:mm:ss.SSS"):"null") +
                '}';
    }
}
