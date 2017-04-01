package com.ffzx.ffsip.model;

import com.ffzx.orm.common.BaseEntity;
import javax.persistence.*;

@Table(name = "wx_article")
public class WxArticle extends BaseEntity {
    /**
     * 文件名
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 链接
     */
    private String url;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 类型：0:个人号；1:企业号
     */
    private String type;

    /**
     * 专题
     */
    private String special;

    /**
     * 转载来源
     */
    private String source;

    /**
     * 标签
     */
    private String label;

    /**
     * 封面
     */
    @Column(name = "cover_img")
    private String coverImg;

    /**
     * 阅读数
     */
    @Column(name = "reading_num")
    private Integer readingNum;

    /**
     * 转发数
     */
    @Column(name = "forwarding_num")
    private Integer forwardingNum;

    /**
     * 评论数
     */
    @Column(name = "comment_num")
    private Integer commentNum;

    /**
     * 首页排序
     */
    @Column(name = "index_sort")
    private Integer indexSort;

    /**
     * 点赞数
     */
    @Column(name = "like_num")
    private Integer likeNum;

    /**
     * 内容
     */
    private String content;

    /**
     * 获取文件名
     *
     * @return name - 文件名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件名
     *
     * @param name 文件名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取链接
     *
     * @return url - 链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置链接
     *
     * @param url 链接
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取发布人
     *
     * @return publisher - 发布人
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 设置发布人
     *
     * @param publisher 发布人
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    /**
     * 获取类型：0:个人号；1:企业号
     *
     * @return type - 类型：0:个人号；1:企业号
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型：0:个人号；1:企业号
     *
     * @param type 类型：0:个人号；1:企业号
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取专题
     *
     * @return special - 专题
     */
    public String getSpecial() {
        return special;
    }

    /**
     * 设置专题
     *
     * @param special 专题
     */
    public void setSpecial(String special) {
        this.special = special == null ? null : special.trim();
    }

    /**
     * 获取转载来源
     *
     * @return source - 转载来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置转载来源
     *
     * @param source 转载来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 获取标签
     *
     * @return label - 标签
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置标签
     *
     * @param label 标签
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    /**
     * 获取封面
     *
     * @return cover_img - 封面
     */
    public String getCoverImg() {
        return coverImg;
    }

    /**
     * 设置封面
     *
     * @param coverImg 封面
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    /**
     * 获取阅读数
     *
     * @return reading_num - 阅读数
     */
    public Integer getReadingNum() {
        return readingNum;
    }

    /**
     * 设置阅读数
     *
     * @param readingNum 阅读数
     */
    public void setReadingNum(Integer readingNum) {
        this.readingNum = readingNum;
    }

    /**
     * 获取转发数
     *
     * @return forwarding_num - 转发数
     */
    public Integer getForwardingNum() {
        return forwardingNum;
    }

    /**
     * 设置转发数
     *
     * @param forwardingNum 转发数
     */
    public void setForwardingNum(Integer forwardingNum) {
        this.forwardingNum = forwardingNum;
    }

    /**
     * 获取评论数
     *
     * @return comment_num - 评论数
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * 设置评论数
     *
     * @param commentNum 评论数
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * 获取首页排序
     *
     * @return index_sort - 首页排序
     */
    public Integer getIndexSort() {
        return indexSort;
    }

    /**
     * 设置首页排序
     *
     * @param indexSort 首页排序
     */
    public void setIndexSort(Integer indexSort) {
        this.indexSort = indexSort;
    }

    /**
     * 获取点赞数
     *
     * @return like_num - 点赞数
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * 设置点赞数
     *
     * @param likeNum 点赞数
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}