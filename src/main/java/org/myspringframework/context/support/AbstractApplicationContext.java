package org.myspringframework.context.support;


import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.factory.ConfigurableListableBeanFactory;
import org.myspringframework.beans.factory.config.BeanFactoryPostProcessor;
import org.myspringframework.beans.factory.config.BeanPostProcessor;
import org.myspringframework.context.ConfigurableApplicationContext;
import org.myspringframework.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    /**
     * 刷新ApplicationContext容器，重新加载BeanDefinition，执行BeanFactoryPostProcessor逻辑，注册BeanPostProcessor，提前实例化Bean
     */
    @Override
    public void refresh() throws BeanException {
        // 创建新的BeanFactory，根据配置文件加载BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 在Bean实例化之前执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // 在其他Bean实例化之前需要首先实例化全部的BeanPostProcessor。因为BeanPostProcessor定义里其他的Bean的初始化方法
        registerBeanPostProcessors(beanFactory);
        // 提前实例化Bean
        beanFactory.preInstantiateSingletons();
    }

    /**
     * Bean示例之前执行BeanFactoryPostProcessor中的方法
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessors = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessors.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册并实例化BeanPostProcessor
     */
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessors = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory() throws BeanException;

    @Override
    public Object getBean(String name) throws BeanException {
        return this.getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> cls) throws BeanException {
        return this.getBeanFactory().getBean(name, cls);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        return this.getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return this.getBeanFactory().getBeanDefinitionNames();
    }


    @Override
    public void close() {
        doClose();
    }

    @Override
    public void registerShutdownHook() {
        Thread shutdownHook = new Thread(this::doClose);
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    private void doClose() {
        destroyBeans();
    }

    private void destroyBeans() {
        getBeanFactory().destroySingletons();
    }
}
