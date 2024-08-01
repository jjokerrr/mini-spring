package org.myspringframework.beans.factory.config;


import org.myspringframework.beans.factory.BeanFactory;

/**
 * 具有配置能力的Bean，例如BeanPostProcessor的Bean对象
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行BeanPostProcessors的postProcessBeforeInitialization方法
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object bean, String name);
    /**
     * 执行BeanPostProcessors的postProcessAfterInitialization方法
     */
    Object applyBeanPostProcessorsAfterInitialization(Object bean, String name);
    
}
