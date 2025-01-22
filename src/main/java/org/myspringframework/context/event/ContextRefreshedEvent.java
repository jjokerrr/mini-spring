package org.myspringframework.context.event;

/**
 * @author zhuangzhihao
 * 容器刷新事件
 * created 2025/1/22
 **/
public class ContextRefreshedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
