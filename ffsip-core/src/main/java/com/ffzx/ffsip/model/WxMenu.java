package com.ffzx.ffsip.model;

import com.ffzx.orm.common.BaseEntity;
import javax.persistence.*;

@Table(name = "wx_menu")
public class WxMenu extends BaseEntity {
    /**
     * 文件名
     */
    private String name;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 链接
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父级id
     */
    @Column(name = "p_code")
    private String pCode;

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
     * @return type - 文件类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置文件类型
     *
     * @param type 文件类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取父级id
     *
     * @return p_code - 父级id
     */
    public String getpCode() {
        return pCode;
    }

    /**
     * 设置父级id
     *
     * @param pCode 父级id
     */
    public void setpCode(String pCode) {
        this.pCode = pCode == null ? null : pCode.trim();
    }
}