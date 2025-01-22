package org.myspringframework.context;

import java.util.EventObject;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 * 基础容器事件
 **/
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
