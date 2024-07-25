package org.myspringframework.context;


import org.myspringframework.bean.exception.BeanException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新BeanFactory，重新加载全部的BeanDefinition
     */
    void refresh() throws BeanException;
}
