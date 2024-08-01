package org.myspringframework.beans.factory.support;


import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.factory.config.BeanDefinition;

/**
 * Bean对象实例化策略
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeanException;
}
