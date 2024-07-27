package org.myspringframework.bean.support;


import org.myspringframework.bean.DisposableBean;
import org.myspringframework.bean.config.SingletonBeanRegistry;
import org.myspringframework.bean.exception.BeanException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * BeanFactory存储单例对象的map
     */
    private final HashMap<String, Object> singletonMap = new HashMap<>();

    /**
     * 具有销毁方法的Bean集合
     */
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

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

    public void registerDisposableBean(String name, DisposableBean bean) {
        disposableBeans.remove(name);
        disposableBeans.put(name, bean);
    }


    /**
     * 执行全部DisposableBean的destroy方法
     */
    public void destroySingletons() {
        Set<String> beanNames = disposableBeans.keySet();
        for (String name : beanNames) {
            DisposableBean disposableBean = disposableBeans.remove(name);
            try {
                disposableBean.destroy();
            } catch (Exception ex) {
                throw new BeanException("Destroy method on bean with name '" + name + "' threw an exception", ex);
            }
        }
    }


}
