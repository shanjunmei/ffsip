package com.ffzx.common.context;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/9.
 */
public class MarkerScaner extends ClassPathBeanDefinitionScanner {

    public MarkerScaner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        processBeanDefinitions(beanDefinitions);
        return beanDefinitions;
    }


    protected void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        Iterator var3 = beanDefinitions.iterator();

        while (var3.hasNext()) {
            BeanDefinitionHolder holder = (BeanDefinitionHolder) var3.next();

            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            //definition.setBeanClass();
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Creating MapperFactoryBean with name \'" + holder.getBeanName() + "\' and \'" + definition.getBeanClassName() + "\' mapperInterface");
            }

           // definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());
            //definition.setBeanClass(this.mapperFactoryBean.getClass());
            definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());
            definition.setBeanClass(MarkerFactoryBean.class);


            definition.setAutowireMode(2);

        }
    }
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}

