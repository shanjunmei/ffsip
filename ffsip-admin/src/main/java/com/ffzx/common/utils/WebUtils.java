package com.ffzx.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/1/20.
 */
public class WebUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpSession getSession(){
        return getRequest().getSession();
    }

    public static void createSession() {
       getRequest().getSession(true);
    }

    public static <T> T getRequestParameter(String key) {
        return (T) getRequest().getParameter(key);
    }

    public static <T> T getSessionAttribute(String key) {
        return (T) getRequest().getSession().getAttribute(key);
    }

    public static void setSessionAttribute(String key,Object val){
        getRequest().getSession().setAttribute(key,val);
    }

}
