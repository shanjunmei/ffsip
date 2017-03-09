package com.ffzx.common.context;

import com.ffzx.ffsip.service.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/9.
 */
@Component
public class BeanRegistry implements BeanDefinitionRegistryPostProcessor{

    private Logger logger= LoggerFactory.getLogger(getClass());

    Class<?> markerClass=Marker.class;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        MarkerScaner scanner=new MarkerScaner(registry);

 /*       scanner.addIncludeFilter(new AssignableTypeFilter(this.markerClass) {
            @Override
            protected boolean matchClassName(String className) {
                return false;
            }
        });*/
        scanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                if(metadataReader.getClassMetadata().getClassName().equals(markerClass.getName())){
                    return false;
                }
                Class<?> clazz = null;
                try {
                    clazz = ClassUtils.forName(metadataReader.getClassMetadata().getClassName(), getClass().getClassLoader());
                } catch (ClassNotFoundException e) {
                    //IGNORE
                }
                return markerClass.isAssignableFrom(clazz);
            }
        });
   /*     scanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });*/
        scanner.scan("com.ffzx.ffsip.service");
        logger.info("registry:{}",registry);
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        logger.info("beanFactory:{}",beanFactory);
    }
}
