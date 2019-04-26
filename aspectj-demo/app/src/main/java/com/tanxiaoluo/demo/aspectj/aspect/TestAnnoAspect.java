package com.tanxiaoluo.demo.aspectj.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TestAnnoAspect {

    private static final String TAG = TestAnnoAspect.class.getSimpleName();

    @Pointcut("execution(* com.tanxiaoluo.demo.aspectj.MainActivity.cutPoint(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        Log.d(TAG, "before");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String key = proceedingJoinPoint.getSignature().toString();
        Log.d(TAG, "around 1," + key);
        proceedingJoinPoint.proceed();
        Log.d(TAG, "around 2: " + key);
    }

    @After("pointcut()")
    public void after() {
        Log.d(TAG, "after");
    }

    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        System.out.println("@afterThrowing");
        Log.d(TAG, String.format("afterThrowing, ex: %s", ex.getMessage()));
    }

}
