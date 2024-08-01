package org.myspringframework.beans.factory;


import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.factory.config.AutowireCapableBeanFactory;
import org.myspringframework.beans.factory.config.BeanDefinition;
import org.myspringframework.beans.factory.config.BeanPostProcessor;
import org.myspringframework.beans.factory.config.ConfigurableBeanFactory;

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
