package com.vynht.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

  // This is where we add all of our related advices for logging
  // Let's start with a @Before advice
//  @Before("execution(public void addAccount())")
//  @Before("execution(public void com.vynht.aopdemo.dao.AccountDAO.addAccount())")
//  @Before("execution(public void add*())")
//  @Before("execution(void add*())")
//  @Before("execution(* add*())")
//  @Before("execution(* add*(com.vynht.aopdemo.Account))")
//  @Before("execution(* add*(com.vynht.aopdemo.Account, ..))")
//  @Before("execution(* add*(..))")
  @Before("execution(* com.vynht.aopdemo.dao.*.*(..))")
  public void beforeAddAccountAdvice() {
    System.out.println("\n=====> Executing @Before advice on method");
  }
}
