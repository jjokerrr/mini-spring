package org.myspringframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author zhuangzhihao
 * created 2025/1/30
 **/
public interface Advisor {
    Advice getAdvice();
}
