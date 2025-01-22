package org.myspringframework.context.event;

import org.myspringframework.context.ApplicationEvent;
import org.myspringframework.context.ApplicationListener;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<ApplicationEvent> listener);

    void removeApplicationListener(ApplicationListener<ApplicationEvent> listener);

    void multicastEvent(ApplicationEvent event);
}
