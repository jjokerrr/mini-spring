package org.myspringframework.context.event;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 * 容器关闭事件
 **/
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
