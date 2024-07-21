package bean;

import bean.config.AutowireCapableBeanFactory;
import bean.config.BeanDefinition;
import bean.config.BeanPostProcessor;
import bean.config.ConfigurableBeanFactory;
import bean.exception.BeanException;

public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, AutowireCapableBeanFactory, ListableBeanFactory {
    /**
     * 根据Bean名称查找BeanDefinition
     */
    BeanDefinition getBeanDefinition(String name) throws BeanException;

    /**
     * 提前实例化所有单例实例
     */
    void preInstantiateSingletons() throws BeanException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
