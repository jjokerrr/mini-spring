package org.myspringframework.beans.factory.support;


import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.factory.FactoryBean;
import org.myspringframework.beans.factory.config.BeanDefinition;
import org.myspringframework.beans.factory.config.BeanPostProcessor;
import org.myspringframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽线BeanFactory，用于创建获取单例Bean对象，在bean对象不存在的时候负责初始化bean对象
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * factoryBean缓存
     */
    private final Map<String, Object> factoryBeanCache = new HashMap<>();

    /**
     * 获取单例对象，如果单例对象map中不存在单例对象，那么通过BeanDefinition创建该对象，是一种Lazy创建的方式
     */
    @Override
    public Object getBean(String name) throws BeanException {
        Object singleton = getSingleton(name);
        if (singleton != null) {
            // 检查是否时FactoryBean对象，如果是则通过FactoryBean获取真正的Bean实例
            return getObjectForBeanInstance(singleton, name);
        }
        // 单例map中不存在该对象，使用BeanDefinition创建对象
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition);
        // 统一获取Bean，区分Bean实例和FactoryBean
        return getObjectForBeanInstance(bean, name);
    }

    /**
     * 获取目标Bean对象，区分Bean实例和FactoryBean
     */
    private Object getObjectForBeanInstance(Object singleton, String name) {
        Object object = singleton;
        if (singleton instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) singleton;
            try {
                if (factoryBean.isSingleton()) {
                    object = factoryBeanCache.get(name);
                    if (object == null) {
                        object = factoryBean.getObject();
                        factoryBeanCache.put(name, object);
                    }
                } else {
                    return factoryBean.getObject();
                }
            } catch (Exception ex) {
                throw new BeanException("FactoryBean threw exception on object[" + name + "] creation", ex);
            }
        }
        return object;
    }

    @Override
    public <T> T getBean(String name, Class<T> cls) throws BeanException {
        return (T) getBean(name);
    }

    /**
     * 创建单例对象
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition) throws BeanException;

    /**
     * 获取BeanDefinition
     */
    protected abstract BeanDefinition getBeanDefinition(String name) throws BeanException;


    /**
     * 存在则更新，不存在则插入
     */
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
