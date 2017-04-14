package com.ffzx.common.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/3/9.
 */
public class MarkerFactoryBean<T> implements FactoryBean<T> {

    Class<T> target;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public MarkerFactoryBean(Class<T> target) {
        this.target = target;
    }


    @Override
    public T getObject() throws Exception {
        T t = MarkerFactoryBeanHolder.get(target);
        if (t == null) {
            t = (T) createIntance();

            MarkerFactoryBeanHolder.set(target, t);
        }
        return t;
    }


    public T createIntance() {
        try {
            Class factory = Class.forName("com.ffzx.common.context.MarkerFactory");
            Method method = factory.getMethod("createIntance", Class.class);
            return (T) method.invoke(null, target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
