package org.myspringframework.beans.factory.config;


import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 修改BeanDefinition的属性定义
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在全部BeanDefinition创建之后可以使用该方法进行BeanDefinition的属性修改
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;
}
