package com.ffzx.weixin.menu;

import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
public class WxMenu {

    private String key;

    private String name;

    private String type;

    private String url;

    private List<WxMenu> sub_button;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<WxMenu> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<WxMenu> sub_button) {
        this.sub_button = sub_button;
    }
}
