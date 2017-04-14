package com.ffzx.common.context;

import com.ffzx.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Administrator on 2017/4/14.
 */
public class MarkerFactory {

    private static Logger logger = LoggerFactory.getLogger(MarkerFactory.class);


    public static Object createIntance(Class cls) {
        logger.info("create BeanProxy for {}", cls);
        String simpleName = cls.getSimpleName().toLowerCase();
        if (simpleName.endsWith("service")) {
            return createServiceIncance(cls);
        }
        return null;
    }

    private static Object createServiceIncance(Class cls) {
        try {
            String className = cls.getName() + "Impl$MarkerGen";

            byte[] data = createServiceImpl(cls, className);

         /*   FileOutputStream fos = new FileOutputStream("d:/" + className + ".class");
            fos.write(data);
            fos.flush();*/

            ProxyGenerator.MarkerLoader loader = new ProxyGenerator.MarkerLoader();
            Class<?> ct = loader.loadClass(className, data);
            Object t = ct.newInstance();
            Field field = ct.getDeclaredField("mapper");
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(t, ProxyGenerator.createBeanProxy(field.getType()));
            field.setAccessible(accessible);
            return t;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static byte[] createServiceImpl(Class cls, String className) {
        String _base = BaseServiceImpl.class.getName();//"com.ffzx.common.service.impl.BaseServiceImpl";
        Class<?> typeClass = (Class<?>) ((ParameterizedType) cls.getGenericInterfaces()[0]).getActualTypeArguments()[0];
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

}
