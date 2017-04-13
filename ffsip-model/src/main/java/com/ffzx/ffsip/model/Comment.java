package com.ffzx.ffsip.model;

import com.ffzx.orm.common.BaseEntity;
import javax.persistence.*;

@Table(name = "comment")
public class Comment extends BaseEntity {
    /**
     * 评论文章
     */
    @Column(name = "article_code")
    private String articleCode;

    /**
     * 评论人
     */
    @Column(name = "member_code")
    private String memberCode;

    /**
     * 昵称
     */
    @Column(name = "wx_nick_name")
    private String wxNickName;

    /**
     * 评论内容
     */
    @Column(name = "comment_content")
    private String commentContent;

    /**
     * 点赞数量
     */
    @Column(name = "like_num")
    private Integer likeNum;

    /**
     * 获取评论文章
     *
     * @return article_code - 评论文章
     */
    public String getArticleCode() {
        return articleCode;
    }

    /**
     * 设置评论文章
     *
     * @param articleCode 评论文章
     */
    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode == null ? null : articleCode.trim();
    }

    /**
     * 获取评论人
     *
     * @return member_code - 评论人
     */
    public String getMemberCode() {
        return memberCode;
    }

    /**
     * 设置评论人
     *
     * @param memberCode 评论人
     */
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode == null ? null : memberCode.trim();
    }

    /**
     * 获取昵称
     *
     * @return wx_nick_name - 昵称
     */
    public String getWxNickName() {
        return wxNickName;
    }

    /**
     * 设置昵称
     *
     * @param wxNickName 昵称
     */
    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName == null ? null : wxNickName.trim();
    }

    /**
     * 获取评论内容
     *
     * @return comment_content - 评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置评论内容
     *
     * @param commentContent 评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    /**
     * 获取点赞数量
     *
     * @return like_num - 点赞数量
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * 设置点赞数量
     *
     * @param likeNum 点赞数量
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }
}