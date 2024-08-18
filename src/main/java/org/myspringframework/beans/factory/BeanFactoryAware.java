package org.myspringframework.beans.factory;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}
