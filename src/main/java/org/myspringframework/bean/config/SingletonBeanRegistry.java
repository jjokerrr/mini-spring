package org.myspringframework.bean.config;

/**
 * 单例对象注册接口
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String name);

    void addSingleton(String name,Object singleton);

}
