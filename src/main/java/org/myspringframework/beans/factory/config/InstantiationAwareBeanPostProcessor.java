package org.myspringframework.beans.factory.config;

import org.myspringframework.beans.BeanException;

/**
 * @author zhuangzhihao
 * created 2025/2/2
 * 代理对象生成bean，在bean实例化之前进行
 **/
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * bean实例化之前执行
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException;
}
