package com.ffzx.common.context;

import com.ffzx.commerce.framework.utils.SpringContextHolder;
import com.ffzx.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.*;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.*;

/**
 * Created by Administrator on 2017/3/9.
 */
public class MarkerFactoryBean<T> implements FactoryBean<T> {

    Class<T> target;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public MarkerFactoryBean(Class<T> target) {
        this.target = target;
    }

    static Object createBeanProxy(Class cls) {
        return Proxy.newProxyInstance(MarkerFactoryBean.class.getClassLoader(), new Class[]{cls}, new BeanProxy(cls));

    }


    @Override
    public T getObject() throws Exception {
        T t = MarkerFactoryBeanHolder.get(target);
        if (t == null) {
            t = (T) createIntance(target);

            MarkerFactoryBeanHolder.set(target, t);
        }
        return t;
    }

    public Object createIntance(Class cls) {
        logger.info("create BeanProxy for {}", cls);
        String simpleName = cls.getSimpleName().toLowerCase();
        if (simpleName.endsWith("service")) {
            return createServiceIncance(cls);
        }
        return null;
    }

    public Object createServiceIncance(Class cls) {
        try {
            String className = cls.getName() + "Impl";

            byte[] data = createServiceImpl(cls, className);

         /*   FileOutputStream fos = new FileOutputStream("d:/" + className + ".class");
            fos.write(data);
            fos.flush();*/

            MarkerLoader loader = new MarkerLoader();
            Class<?> ct = loader.loadClass(className, data);
            Object t = ct.newInstance();
            Field field = ct.getDeclaredField("mapper");
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(t, createBeanProxy(field.getType()));
            field.setAccessible(accessible);
            return t;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public byte[] createServiceImpl(Class cls, String className) {
        String _base = BaseServiceImpl.class.getName();//"com.ffzx.common.service.impl.BaseServiceImpl";
        Class<?> typeClass = (Class<T>) ((ParameterizedType) cls.getGenericInterfaces()[0]).getActualTypeArguments()[0];
        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, className.replace(".", "/"), "L" + _base.replace(".", "/") + "<L" + typeClass.getName().replace(".", "/") + ";Ljava/lang/String;>;L" + cls.getName().replace(".", "/") + ";", _base.replace(".", "/"), new String[]{cls.getName().replace(".", "/")});

        {
            av0 = cw.visitAnnotation("Lorg/springframework/stereotype/Service;", true);
            av0.visitEnd();
        }
        {
            fv = cw.visitField(Opcodes.ACC_PRIVATE, "mapper", "Lcom/ffzx/ffsip/mapper/" + typeClass.getSimpleName() + "Mapper;", null, null);
            {
                av0 = fv.visitAnnotation("Ljavax/annotation/Resource;", true);
                av0.visitEnd();
            }
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, _base.replace(".", "/"), "<init>", "()V", false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "getMapper", "()Lcom/ffzx/ffsip/mapper/" + typeClass.getSimpleName() + "Mapper;", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitFieldInsn(Opcodes.GETFIELD, className.replace(".", "/"), "mapper", "Lcom/ffzx/ffsip/mapper/" + typeClass.getSimpleName() + "Mapper;");
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_BRIDGE + Opcodes.ACC_SYNTHETIC, "getMapper", "()Ltk/mybatis/mapper/common/Mapper;", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, className.replace(".", "/"), "getMapper", "()Lcom/ffzx/ffsip/mapper/" + typeClass.getSimpleName() + "Mapper;", false);
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }


    public T createIntance() {
        return (T) createIntance(target);
    }

    @Override
    public Class<T> getObjectType() {
        return target;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    static class BeanProxy implements InvocationHandler {

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


    static class MarkerLoader extends ClassLoader {

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
}
