package org.myspringframework.event;

import org.myspringframework.context.ApplicationListener;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(event.getClass().getName());
    }
}
