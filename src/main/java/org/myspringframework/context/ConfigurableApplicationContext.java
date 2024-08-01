package org.myspringframework.context;


import org.myspringframework.beans.BeanException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新BeanFactory，重新加载全部的BeanDefinition
     */
    void refresh() throws BeanException;


    /**
     * 容器关闭方法，执行所有Bean的销毁方法
     */
    void close();

    /**
     * 向虚拟机中注册一个销毁钩子，该方法会在虚拟机退出的时候执行关闭容器操作
     */
    void registerShutdownHook();
}
