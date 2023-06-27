package org.zjy.diveintoive.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.zjy.diveintoive.DiveintoiveApplication;


@Aspect
@Component
public class AOPUtil {
    private final Logger logger = LogManager.getLogger(DiveintoiveApplication.class);

//    @Before(value="execution(public * org.zjy.diveintoive.*.*.*(..))")
//    public void serviceFunctionBefore(JoinPoint joinPoint){
//        logger.info("\"inner function before\" AOP triggered: "+joinPoint.getSignature().getDeclaringType().getName()+"."+joinPoint.getSignature().getName());
//    }

    @Before(value="execution(public * org.zjy.diveintoive.*.*(..))")
    public void gatewayFunctionBefore(JoinPoint joinPoint){
        logger.info("\"gateway function before\" AOP triggered: "+joinPoint.getSignature().getDeclaringType().getName()+"."+joinPoint.getSignature().getName());
    }

}
