package org.myspringframework.beans.factory;

/**
 * FactoryBean,工厂Bean对象，每次通过调用getObject方法获取bean对象
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    boolean isSingleton();
}
