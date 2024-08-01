package org.myspringframework.beans.factory;


import org.myspringframework.beans.BeanException;

public interface BeanFactory {


    /**
     * 获取Bean对象
     */
    Object getBean(String name) throws BeanException;

    /**
     * 根据类型获取Bean
     */
    <T> T getBean(String name, Class<T> cls) throws BeanException;
}
