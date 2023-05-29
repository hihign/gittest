package com.powernode.spring6.service1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component("myAspect1")
@Aspect
@Order(1)
public class MyAspect1 {
    //声明切点表达式，以便调用
    @Pointcut("execution(* com.powernode.spring6.service1.TransferService.*(..))")
    public void samePointcut(){}

    @AfterReturning("samePointcut()")
    public void advice(){
        System.out.println("后置增强代码");
    }
    @Around("samePointcut()")
    public void adviceAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕增强代码1");
        proceedingJoinPoint.proceed();
        System.out.println("环绕增强代码2");
    }
    @After("samePointcut()")
    public void adviceAfter(){
        System.out.println("最终增强代码");
    }
    @AfterThrowing("samePointcut()")
    public void adviceThrowing(){
        System.out.println("抛出异常增强代码");
    }

}
