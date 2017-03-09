package com.ffzx.commerce.framework.model;

/**
 * Created by Administrator on 2017/2/20.
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {

    }


    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable e) {
        super(msg, e);
    }

}
