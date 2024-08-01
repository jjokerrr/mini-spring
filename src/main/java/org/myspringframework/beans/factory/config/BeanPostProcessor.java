package org.myspringframework.beans.factory.config;

/**
 * 在Bean对象实例化之后初始化之前对Bean对象进行修改
 */
public interface BeanPostProcessor {

    /**
     * Bean实例化之后初始化之前，修改Bean属性
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * Bean实例化之后初始化之前，修改Bean属性
     */
    Object postProcessAfterInitialization(Object bean, String beanName);


}
