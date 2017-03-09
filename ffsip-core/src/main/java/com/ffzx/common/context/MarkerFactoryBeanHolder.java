package com.ffzx.common.context;

import com.ffzx.ffsip.service.Marker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/9.
 */
public class MarkerFactoryBeanHolder {

    public static Map<Class<Marker>, Object> holder = new HashMap();

    public static <T> T get(Class<T> cls) {
        return (T) holder.get(cls);
    }

    public static <T> void set(Class<T> cls, T obj) {
        holder.put((Class<Marker>) cls, obj);
    }

}
