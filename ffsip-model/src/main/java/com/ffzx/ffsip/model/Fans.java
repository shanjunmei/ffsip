package com.ffzx.ffsip.model;

import com.ffzx.orm.common.BaseEntity;
import javax.persistence.*;

@Table(name = "fans")
public class Fans extends BaseEntity {
    /**
     * 被关注人code
     */
    @Column(name = "subscribe_code")
    private String subscribeCode;

    /**
     * 粉丝code
     */
    @Column(name = "fans_code")
    private String fansCode;

    /**
     * 获取被关注人code
     *
     * @return subscribe_code - 被关注人code
     */
    public String getSubscribeCode() {
        return subscribeCode;
    }

    /**
     * 设置被关注人code
     *
     * @param subscribeCode 被关注人code
     */
    public void setSubscribeCode(String subscribeCode) {
        this.subscribeCode = subscribeCode == null ? null : subscribeCode.trim();
    }

    /**
     * 获取粉丝code
     *
     * @return fans_code - 粉丝code
     */
    public String getFansCode() {
        return fansCode;
    }

    /**
     * 设置粉丝code
     *
     * @param fansCode 粉丝code
     */
    public void setFansCode(String fansCode) {
        this.fansCode = fansCode == null ? null : fansCode.trim();
    }
}