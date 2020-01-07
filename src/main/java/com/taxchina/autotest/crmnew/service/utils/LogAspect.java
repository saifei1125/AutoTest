package com.taxchina.autotest.crmnew.service.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspect {
    /**
     * 定义一个方法, 用于声明切入点表达式. 一般地,
     *该方法中再不需要添入其他的代码.
     * 使用 @Pointcut 来声明切入点表达式.
     * 后面的其他通知直接使用方法名来引用当前的切入点表达式.
     */
    @Pointcut("execution(* service.impl.*.*(..))")
    public void declareJointPointExpression(){}

    /**
     * 前置通知
     * 在 com.atguigu.spring.aop.ArithmeticCalculator
     * 接口的每一个实现类的每一个方法开始之前执行一段代码
     * 用通配符*来表示所有
     */
    @Before("declareJointPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
//        Log.info("前置。。。。。。。。。。");
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Log.info("before method " + methodName + " begin with:" + Arrays.asList(args));
    }

    /**
     * 后置通知
     * 在方法执行之后执行的代码. 无论该方法是否出现异常
     * @param joinPoint
     */

    @After("declareJointPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
//        Log.info("后置。。。。。。。。。。");
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Log.info("after method " + methodName + " end " + Arrays.asList(args));
    }

//    /**
//     * 返回通知
//     * 在方法法正常结束受执行的代码
//     * 返回通知是可以访问到方法的返回值的!
//     */
//    @AfterReturning(value = "declareJointPointExpression()",
//            returning = "result")
//    public void afterReturning(JoinPoint joinPoint,
//                               Object result) {
//        String methodName = joinPoint.getSignature().getName();
//        System.out.println("The method " + methodName +
//                " ends with " + result);
//    }

    /**
     * 异常通知
     * 在目标方法出现异常时会执行的代码.
     * 可以访问到异常对象; 且可以指定在出现特定异常时在执行通知代码
     */
    @AfterThrowing(value = "declareJointPointExpression()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        Log.error("The method " + methodName + " occurs excetion:" + e);
    }

//    /**
//     * 环绕通知需要携带 ProceedingJoinPoint 类型的参数.
//     * 环绕通知类似于动态代理的全过程: ProceedingJoinPoint 类型的参数可以决定是否执行目标方法.
//     * 且环绕通知必须有返回值, 返回值即为目标方法的返回值
//     */
//    @Around("declareJointPointExpression())")
//    public Object aroundMethod(ProceedingJoinPoint pjd) {
//
//        Object result = null;
//        String methodName = pjd.getSignature().getName();
//
//        try {
//            //前置通知
//            System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
//            //执行目标方法
//            result = pjd.proceed();
//            //返回通知
//            System.out.println("The method " + methodName + " ends with " + result);
//        } catch (Throwable e) {
//            //异常通知
//            System.out.println("The method " + methodName + " occurs exception:" + e);
//            throw new RuntimeException(e);
//        }
//        //后置通知
//        System.out.println("The method " + methodName + " ends");
//
//        return result;
//    }

}