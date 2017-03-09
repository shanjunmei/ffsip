package com.ffzx.common.utils;

import java.io.Serializable;
import java.util.Map;

public class ResultVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -9079848026448006367L;

    // 状态
    private String status;

    // 跳转URL
    private String url;

    // 返回多条提示信息
    private Map<String, String> infoMap;

    // 返回提示信息
    private String infoStr;

    // 返回数据
    private Object infoData;

    //错误信息(异常堆栈信息)，不直接显示给用户。
    private String infoError;

    // 是否有异常，true表示有异常。主要用于后台。
    private boolean hasException;

    //是否使用父级窗体跳转界面
    private String ifParentHref = "false";

    //列表分页时使用的总记录数
    private Integer recordsTotal;


    public String getIfParentHref() {
        return ifParentHref;
    }

    public void setIfParentHref(String ifParentHref) {
        this.ifParentHref = ifParentHref;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the infoMap
     */
    public Map<String, String> getInfoMap() {
        return infoMap;
    }

    /**
     * @param infoMap the infoMap to set
     */
    public void setInfoMap(Map<String, String> infoMap) {
        this.infoMap = infoMap;
    }

    /**
     * @return the infoStr
     */
    public String getInfoStr() {
        return infoStr;
    }

    /**
     * @param infoStr the infoStr to set
     */
    public void setInfoStr(String infoStr) {
        this.infoStr = infoStr;
    }

    /**
     * @return the hasException
     */
    public boolean isHasException() {
        return hasException;
    }

    /**
     * @param hasException the hasException to set
     */
    public void setHasException(boolean hasException) {
        this.hasException = hasException;
    }

    /**
     * @return the infoError
     */
    public String getInfoError() {
        return infoError;
    }

    /**
     * @param infoError the infoError to set
     */
    public void setInfoError(String infoError) {
        this.infoError = infoError;
    }

    /**
     * @return the infoData
     */
    public Object getInfoData() {
        return infoData;
    }

    /**
     * @param infoData the infoData to set
     */
    public void setInfoData(Object infoData) {
        this.infoData = infoData;
    }

    /**
     * @return the recordsTotal
     */
    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    /**
     * @param recordsTotal the recordsTotal to set
     */
    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

}
