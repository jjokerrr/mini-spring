package org.myspringframework.aop;

/**
 * @author zhuangzhihao
 * created 2025/1/24
 **/
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
