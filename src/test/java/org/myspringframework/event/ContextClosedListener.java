package org.myspringframework.event;

import org.myspringframework.context.ApplicationListener;
import org.myspringframework.context.event.ContextClosedEvent;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public class ContextClosedListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(event.getClass().getName());
    }
}
