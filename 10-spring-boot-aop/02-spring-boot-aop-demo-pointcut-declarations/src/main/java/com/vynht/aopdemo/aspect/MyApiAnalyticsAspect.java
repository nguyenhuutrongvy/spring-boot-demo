package com.vynht.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

  @Before("com.vynht.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
  public void performApiAnalytics() {
    System.out.println("=====> Performing API analytics");
  }
}
