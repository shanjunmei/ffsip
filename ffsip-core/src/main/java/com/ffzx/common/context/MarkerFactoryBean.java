package com.ffzx.common.context;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/3/9.
 */
public class MarkerFactoryBean<T> implements FactoryBean<T>{

    Class<T> target;

    public MarkerFactoryBean(Class<T> target){
        this.target=target;
    }

    @Override
    public T getObject() throws Exception {
        T t= MarkerFactoryBeanHolder.get(target);
        if(t==null){
            t=(T)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{target}, new InvocationHandler() {
                @Override
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    System.out.println("hello proxy");
                    return null;
                }
            });
            MarkerFactoryBeanHolder.set(target,t);
        }
        return t;
    }


    @Override
    public Class<T> getObjectType() {
        return target;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
