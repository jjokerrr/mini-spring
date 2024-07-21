package bean.support;

import bean.config.BeanDefinition;
import bean.config.BeanPostProcessor;
import bean.config.ConfigurableBeanFactory;
import bean.exception.BeanException;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽线BeanFactory，用于创建获取单例Bean对象，在bean对象不存在的时候负责初始化bean对象
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

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

    @Override
    public <T> T getBean(String name, Class<T> cls) {
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
