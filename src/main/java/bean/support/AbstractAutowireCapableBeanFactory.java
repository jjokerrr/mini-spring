package bean.support;

import bean.config.BeanDefinition;
import bean.exception.BeanException;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeanException {
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            bean = beanClass.newInstance();
        } catch (Exception e) {
            throw new BeanException("Instantiation of bean failed", e);
        }

        addSingleton(name, bean);
        return bean;

    }

}
