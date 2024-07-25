package org.myspringframework.context.support;

import org.myspringframework.bean.exception.BeanException;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private String[] configLocations;

    /**
     * 从单xml文件中加载，刷新上下文
     */
    public ClassPathXmlApplicationContext(String configLocation) throws BeanException {
        this(new String[]{configLocation});
    }

    /**
     * 从多个xml文件中加载，刷新上下文
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeanException {
        this.configLocations = configLocations;
        refresh();
    }


    @Override
    public String[] getConfigLocations() {
        return configLocations;
    }
}
