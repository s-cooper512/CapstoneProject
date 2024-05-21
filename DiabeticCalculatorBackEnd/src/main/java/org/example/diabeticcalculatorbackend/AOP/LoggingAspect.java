package org.example.diabeticcalculatorbackend.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* org.example.diabeticcalculatorbackend.*.*(..))")
    public Object logServiceMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        logger.info("Executing {}() method in {} service", methodName, className);

        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("Finished executing {}() method in {} ms", methodName, executionTime);

        return result;
    }
}