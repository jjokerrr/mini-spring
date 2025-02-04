package org.myspringframework.context.support;


import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.factory.ConfigurableListableBeanFactory;
import org.myspringframework.beans.factory.config.BeanFactoryPostProcessor;
import org.myspringframework.beans.factory.config.BeanPostProcessor;
import org.myspringframework.context.ApplicationEvent;
import org.myspringframework.context.ApplicationListener;
import org.myspringframework.context.ConfigurableApplicationContext;
import org.myspringframework.context.event.ApplicationEventMulticaster;
import org.myspringframework.context.event.ContextClosedEvent;
import org.myspringframework.context.event.ContextRefreshedEvent;
import org.myspringframework.context.event.SimpleApplicationEventMulticaster;
import org.myspringframework.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    /**
     * 刷新ApplicationContext容器，重新加载BeanDefinition，执行BeanFactoryPostProcessor逻辑，注册BeanPostProcessor，提前实例化Bean
     */
    @Override
    public void refresh() throws BeanException {
        // 创建新的BeanFactory，根据配置文件加载BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 添加ApplicationContextAwareProcessor，使bean对象能够感知ApplicationContextAware
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // 在Bean实例化之前执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // 在其他Bean实例化之前需要首先实例化全部的BeanPostProcessor。因为BeanPostProcessor定义里其他的Bean的初始化方法
        registerBeanPostProcessors(beanFactory);
        // 初始化事件发布者
        initApplicationEventMulticaster();
        // 初始化事件监听器
        registerListeners();
        // 提前实例化Bean
        beanFactory.preInstantiateSingletons();
        // 发布容器创建完成事件
        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void registerListeners() {
        getBeansOfType(ApplicationListener.class).values().forEach(listener -> {
            applicationEventMulticaster.addApplicationListener(listener);
        });

    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        // 注册成Spring bean对象
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
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

        //发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        destroyBeans();
    }

    private void destroyBeans() {
        getBeanFactory().destroySingletons();
    }
}
