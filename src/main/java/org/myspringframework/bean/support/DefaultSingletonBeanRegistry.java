package org.myspringframework.bean.support;


import org.myspringframework.bean.config.SingletonBeanRegistry;

import java.util.HashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    HashMap<String, Object> singletonMap = new HashMap<>();

    /**
     * 根据名称获取单例对象
     */
    @Override
    public Object getSingleton(String name) {
        return singletonMap.get(name);
    }

    @Override
    public void addSingleton(String name, Object singleton) {
        singletonMap.put(name, singleton);
    }
}
