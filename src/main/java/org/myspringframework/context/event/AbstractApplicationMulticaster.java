package org.myspringframework.context.event;

import org.myspringframework.beans.factory.BeanFactory;
import org.myspringframework.beans.factory.BeanFactoryAware;
import org.myspringframework.context.ApplicationEvent;
import org.myspringframework.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public abstract class AbstractApplicationMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    protected final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<ApplicationEvent> listener) {
        applicationListeners.add(listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<ApplicationEvent> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
