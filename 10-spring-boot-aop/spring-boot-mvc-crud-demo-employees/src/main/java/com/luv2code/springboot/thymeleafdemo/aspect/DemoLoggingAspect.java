package com.luv2code.springboot.thymeleafdemo.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {

  // Setup logger
  private Logger myLogger = Logger.getLogger(getClass().getName());

  // Setup pointcut declarations
  @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
  private void forControllerPackage() {
  }

  @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
  private void forDaoPackage() {
  }

  @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
  private void forServicePackage() {
  }

  @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
  private void forAppFlow() {
  }

  // Add @Before advice
  @Before("forAppFlow()")
  public void before(JoinPoint theJoinPoint) {
    // Display method we are calling
    String theMethod = theJoinPoint.getSignature().toShortString();
    myLogger.info("=====> @Before: calling method: " + theMethod);

    // Display the arguments to the method
    // Get the arguments
    Object[] args = theJoinPoint.getArgs();

    // Loop through and display args
    for (Object tempArg : args) {
      myLogger.info("=====> Argument: " + tempArg);
    }
  }

  // Add @AfterReturning advice
  @AfterReturning(
      pointcut = "forAppFlow()",
      returning = "theResult"
  )
  public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
    // Display method we are returning from
    String theMethod = theJoinPoint.getSignature().toShortString();
    myLogger.info("=====> @AfterReturning: from method: " + theMethod);

    // Display data returned
    myLogger.info("=====> The result: " + theResult);
  }
}
