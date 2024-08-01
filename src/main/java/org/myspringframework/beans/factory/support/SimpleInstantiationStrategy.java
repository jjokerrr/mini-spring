package org.myspringframework.beans.factory.support;


import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.factory.config.BeanDefinition;

/**
 * 简单实例化方法
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * 根据默认构造方法创建对象实例
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeanException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            return beanClass.newInstance();
        } catch (Exception e) {
            throw new BeanException("Fail to instantiate " + beanClass.getName(), e);
        }
    }
}
