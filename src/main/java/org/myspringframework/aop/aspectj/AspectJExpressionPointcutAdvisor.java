package org.myspringframework.aop.aspectj;

import org.aopalliance.aop.Advice;
import org.myspringframework.aop.PointCutAdvisor;
import org.myspringframework.aop.Pointcut;

/**
 * @author zhuangzhihao
 * created 2025/1/30
 **/
public class AspectJExpressionPointcutAdvisor implements PointCutAdvisor {

    private String expression;

    private Advice advice;

    private Pointcut pointcut;


    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            this.pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    private String getExpression() {
        return expression;
    }
}
