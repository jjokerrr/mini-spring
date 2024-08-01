package org.myspringframework.beans.factory.support;

import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.factory.ConfigurableListableBeanFactory;
import org.myspringframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * BeanFactory 实现类对象，注册BeanDefinition，维护单例SingletonBeanMap，采用Lazy加载模式
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    HashMap<String, BeanDefinition> beanDefinitionHashMap = new HashMap<>();

    public void registerBeanDefinition(String name, Class cls) {
        BeanDefinition beanDefinition = new BeanDefinition(cls);
        beanDefinitionHashMap.put(name, beanDefinition);

    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionHashMap.put(name, beanDefinition);

    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return beanDefinitionHashMap.containsKey(name);
    }


    @Override
    public BeanDefinition getBeanDefinition(String name) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionHashMap.get(name);
        if (beanDefinition == null) {
            throw new BeanException("no bean named " + name + " is defined!");
        }
        return beanDefinition;

    }

    /**
     * 提前实例化全部的Bean对象
     */
    @Override
    public void preInstantiateSingletons() throws BeanException {
        beanDefinitionHashMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        Map<String, T> beanMap = new HashMap<>();
        beanDefinitionHashMap.forEach((beanName, beanDefinition) -> {
            if (type.isAssignableFrom(beanDefinition.getBeanClass())) {
                T bean = getBean(beanName, type);
                beanMap.put(beanName, bean);
            }
        });
        return beanMap;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanDefinitionNames = this.beanDefinitionHashMap.keySet();
        return beanDefinitionNames.toArray(new String[beanDefinitionNames.size()]);
    }


}
