package com.ffzx.ffsip.model;

import com.ffzx.orm.common.BaseEntity;
import javax.persistence.*;

@Table(name = "like_record")
public class LikeRecord extends BaseEntity {
    /**
     * 文章或评论code 
     */
    @Column(name = "article_or_comment")
    private String articleOrComment;

    /**
     * 点赞人
     */
    @Column(name = "member_code")
    private String memberCode;

    /**
     * 获取文章或评论code 
     *
     * @return article_or_comment - 文章或评论code 
     */
    public String getArticleOrComment() {
        return articleOrComment;
    }

    /**
     * 设置文章或评论code 
     *
     * @param articleOrComment 文章或评论code 
     */
    public void setArticleOrComment(String articleOrComment) {
        this.articleOrComment = articleOrComment == null ? null : articleOrComment.trim();
    }

    /**
     * 获取点赞人
     *
     * @return member_code - 点赞人
     */
    public String getMemberCode() {
        return memberCode;
    }

    /**
     * 设置点赞人
     *
     * @param memberCode 点赞人
     */
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode == null ? null : memberCode.trim();
    }
}