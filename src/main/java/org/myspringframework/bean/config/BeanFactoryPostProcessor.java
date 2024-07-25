package org.myspringframework.bean.config;


import org.myspringframework.bean.ConfigurableListableBeanFactory;
import org.myspringframework.bean.exception.BeanException;

/**
 * 修改BeanDefinition的属性定义
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在全部BeanDefinition创建之后可以使用该方法进行BeanDefinition的属性修改
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;
}
