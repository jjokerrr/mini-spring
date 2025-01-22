package org.myspringframework.context.event;

import org.myspringframework.context.ApplicationContext;
import org.myspringframework.context.ApplicationEvent;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public abstract class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
