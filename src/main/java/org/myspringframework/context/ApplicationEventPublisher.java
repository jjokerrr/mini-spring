package org.myspringframework.context;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
