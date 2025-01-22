package org.myspringframework.event;

import org.myspringframework.context.ApplicationListener;
import org.myspringframework.context.event.ContextRefreshedEvent;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(event.getClass().getName());
    }
}
