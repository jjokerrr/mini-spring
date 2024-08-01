package org.myspringframework.beans.factory;


import org.myspringframework.beans.BeanException;

import java.util.Map;

/**
 * 用于批量处理和检索 Bean 定义
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 返回指定类型的全部实例，包括全部子类型
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;

    /**
     * 返回全部定义的Bean名称
     */
    String[] getBeanDefinitionNames();

}
