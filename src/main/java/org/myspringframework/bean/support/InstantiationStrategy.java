package org.myspringframework.bean.support;


import org.myspringframework.bean.config.BeanDefinition;
import org.myspringframework.bean.exception.BeanException;

/**
 * Bean对象实例化策略
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeanException;
}
