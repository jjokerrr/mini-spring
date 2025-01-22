package org.myspringframework.context;

import java.util.EventListener;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);
}
