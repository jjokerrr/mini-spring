package org.myspringframework.bean.config;


import org.myspringframework.bean.HierarchicalBeanFactory;

/**
 * 提供了对BeanFactory的配置能力,进行设置和管理Bean定义、作用域、后处理器等
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * Bean实例化后初始化之前执行Bean属性设置
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 执行Bean的销毁方法
     */
    void destroySingletons();
}
