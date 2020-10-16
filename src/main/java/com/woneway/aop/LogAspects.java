package com.woneway.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/13 6:26 下午
 * @description
 */
@Aspect
public class LogAspects {
    @Pointcut("execution(public int com.woneway.aop.MathCalculator.*(..))")
    public void pointcut() {

    }

    // 在目标方法之前切入，切入点表达式(置顶在哪个方法切入)
    @Before("pointcut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature() + ", 除法运行。。。参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

    @After("pointcut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature() + ",除法结束。。。");
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature() + ",除法运行。。。返回值：" + result);
    }

    @AfterThrowing(value = "pointcut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature() + ",除法运行。。。异常信息：" + exception);
    }
}
