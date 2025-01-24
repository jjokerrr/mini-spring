package org.myspringframework.service;

import org.myspringframework.beans.factory.BeanFactory;
import org.myspringframework.beans.factory.BeanFactoryAware;
import org.myspringframework.context.ApplicationContext;
import org.myspringframework.context.ApplicationContextAware;

public class HelloService implements ApplicationContextAware, BeanFactoryAware {
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void sayHello() {
        System.out.println("Hello World");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
