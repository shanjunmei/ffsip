package com.ffzx.common.context;

import com.ffzx.commerce.framework.utils.SpringContextHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/4/14.
 */
public abstract class ProxyGenerator {

  public  static class BeanProxy implements InvocationHandler {

        Class target;
        Object intance;

        public BeanProxy(Class cls) {
            this.target = cls;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (intance == null) {
                intance = SpringContextHolder.getBean(target);
            }
            return method.invoke(intance, args);
        }
    }

    public static class MarkerLoader extends ClassLoader {

        public MarkerLoader() {
            super(Thread.currentThread().getContextClassLoader());
        }

        public Class<?> loadClass(String name, byte[] body) {
            try {
                ClassLoader loader = getParent();
                Class<?> cls = ClassLoader.class;
                Method method = cls.getDeclaredMethod(
                        "defineClass", new Class[]{String.class, byte[].class, int.class, int.class});
                boolean accessible = method.isAccessible();
                method.setAccessible(true);
                Object[] args = new Object[]{name, body, new Integer(0), new Integer(body.length)};
                Class clazz = (Class<?>) method.invoke(loader, args);
                method.setAccessible(accessible);
                return clazz;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //  return defineClass(name, body, 0, body.length);
        }
    }

   public static Object createBeanProxy(Class cls) {
        return Proxy.newProxyInstance(MarkerFactoryBean.class.getClassLoader(), new Class[]{cls}, new ProxyGenerator.BeanProxy(cls));

    }
}
