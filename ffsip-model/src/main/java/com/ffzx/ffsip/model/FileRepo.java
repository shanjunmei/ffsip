package com.ffzx.ffsip.model;

import com.ffzx.orm.common.BaseEntity;
import javax.persistence.*;

@Table(name = "file_repo")
public class FileRepo extends BaseEntity {
    /**
     * 文件名
     */
    private String name;

    /**
     * 文件类型
     */
    @Column(name = "content_type")
    private String contentType;

    /**
     * 文件内容
     */
    private byte[] content;

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
     * 获取文件类型
     *
     * @return content_type - 文件类型
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 设置文件类型
     *
     * @param contentType 文件类型
     */
    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    /**
     * 获取文件内容
     *
     * @return content - 文件内容
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * 设置文件内容
     *
     * @param content 文件内容
     */
    public void setContent(byte[] content) {
        this.content = content;
    }
}