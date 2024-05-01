package org.zjy.diveintoive.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AOPUtil {
    private final Logger LOGGER = LogManager.getLogger(AOPUtil.class);

//    @Before(value="execution(public * org.zjy.diveintoive.*.*.*(..))")
//    public void serviceFunctionBefore(JoinPoint joinPoint){
//        logger.info("\"inner function before\" AOP triggered: "+joinPoint.getSignature().getDeclaringType().getName()+"."+joinPoint.getSignature().getName());
//    }

//    @Before(value="execution(public * org.zjy.diveintoive.controller.*.*(..))")
//    public void gatewayFunctionBefore(JoinPoint joinPoint){
//        LOGGER.info("\"gateway function before\" AOP triggered: "+joinPoint.getSignature().getDeclaringType().getName()+"."+joinPoint.getSignature().getName());
//    }

}
