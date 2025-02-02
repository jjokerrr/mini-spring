package org.myspringframework.aop;

/**
 * @author zhuangzhihao
 * created 2025/1/30
 **/
public interface PointCutAdvisor extends Advisor{
    Pointcut getPointcut();
}
