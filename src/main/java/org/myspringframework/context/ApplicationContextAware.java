package org.myspringframework.context;

import org.myspringframework.beans.factory.Aware;

/**
 * Bean对象可实现对ApplicationContext的感知
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext);
}
