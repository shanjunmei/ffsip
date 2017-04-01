package com.ffzx.ffsip.search;

/**
 * Created by Administrator on 2017/4/1.
 */
public class PageHolder {

    private static ThreadLocal<Integer> totalMap=new ThreadLocal<>();

    public static Integer getTotal(){
        return totalMap.get();
    }

    public static void setTotal(Integer total){
        totalMap.set(total);
    }
}
