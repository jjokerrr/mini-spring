package org.myspringframework.event;

import org.myspringframework.context.event.ApplicationContextEvent;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public class CustomEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CustomEvent(Object source) {
        super(source);
    }
}
