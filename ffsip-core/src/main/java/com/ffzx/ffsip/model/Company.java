package com.ffzx.ffsip.model;

import com.ffzx.orm.common.BaseEntity;
import javax.persistence.*;

@Table(name = "company")
public class Company extends BaseEntity {
    /**
     * 绑定会员编码
     */
    @Column(name = "member_code")
    private String memberCode;

    /**
     * 姓名
     */
    private String name;

    /**
     * 简称
     */
    private String abbreviation;

    /**
     * 公众号二维码图片
     */
    @Column(name = "wx_img")
    private String wxImg;

    /**
     * logo图片
     */
    @Column(name = "logo_img")
    private String logoImg;

    /**
     * 文章数
     */
    @Column(name = "article_num")
    private Integer articleNum;

    /**
     * 介绍
     */
    private String introduction;

    /**
     * 获取绑定会员编码
     *
     * @return member_code - 绑定会员编码
     */
    public String getMemberCode() {
        return memberCode;
    }

    /**
     * 设置绑定会员编码
     *
     * @param memberCode 绑定会员编码
     */
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode == null ? null : memberCode.trim();
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取简称
     *
     * @return abbreviation - 简称
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * 设置简称
     *
     * @param abbreviation 简称
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }

    /**
     * 获取公众号二维码图片
     *
     * @return wx_img - 公众号二维码图片
     */
    public String getWxImg() {
        return wxImg;
    }

    /**
     * 设置公众号二维码图片
     *
     * @param wxImg 公众号二维码图片
     */
    public void setWxImg(String wxImg) {
        this.wxImg = wxImg == null ? null : wxImg.trim();
    }

    /**
     * 获取logo图片
     *
     * @return logo_img - logo图片
     */
    public String getLogoImg() {
        return logoImg;
    }

    /**
     * 设置logo图片
     *
     * @param logoImg logo图片
     */
    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg == null ? null : logoImg.trim();
    }

    /**
     * 获取文章数
     *
     * @return article_num - 文章数
     */
    public Integer getArticleNum() {
        return articleNum;
    }

    /**
     * 设置文章数
     *
     * @param articleNum 文章数
     */
    public void setArticleNum(Integer articleNum) {
        this.articleNum = articleNum;
    }

    /**
     * 获取介绍
     *
     * @return introduction - 介绍
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置介绍
     *
     * @param introduction 介绍
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}