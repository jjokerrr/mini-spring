package org.myspringframework.bean;


import org.myspringframework.bean.config.AutowireCapableBeanFactory;
import org.myspringframework.bean.config.BeanDefinition;
import org.myspringframework.bean.config.BeanPostProcessor;
import org.myspringframework.bean.config.ConfigurableBeanFactory;
import org.myspringframework.bean.exception.BeanException;

public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, AutowireCapableBeanFactory, ListableBeanFactory {
    /**
     * 根据Bean名称查找BeanDefinition
     */
    BeanDefinition getBeanDefinition(String name) throws BeanException;

    /**
     * 提前实例化所有单例实例
     */
    void preInstantiateSingletons() throws BeanException;

    /**
     * 添加BeanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
