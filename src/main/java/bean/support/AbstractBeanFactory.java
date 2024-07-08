package bean.support;

import bean.BeanFactory;
import bean.config.BeanDefinition;
import bean.exception.BeanException;

/**
 * 抽线BeanFactory，用于创建获取单例Bean对象，在bean对象不存在的时候负责初始化bean对象
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    /**
     * 获取单例对象，如果单例对象map中不存在单例对象，那么通过BeanDefinition创建该对象，是一种Lazy创建的方式
     */
    @Override
    public Object getBean(String name) throws BeanException {
        Object singleton = getSingleton(name);
        if (singleton != null) {
            return singleton;
        }
        // 单例map中不存在该对象，使用BeanDefinition创建对象
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    /**
     * 创建单例对象
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition) throws BeanException;

    /**
     * 获取BeanDefinition
     */
    protected abstract BeanDefinition getBeanDefinition(String name) throws BeanException;


}
