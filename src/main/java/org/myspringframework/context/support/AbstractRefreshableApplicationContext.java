package org.myspringframework.context.support;


import org.myspringframework.bean.exception.BeanException;
import org.myspringframework.bean.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    /**
     * 刷新BeanFactory容器，加载全部的BeanDefinition
     */
    @Override
    protected void refreshBeanFactory() throws BeanException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 加载BeanDefinition
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeanException;

    /**
     * 创建空的BeanFactory
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
