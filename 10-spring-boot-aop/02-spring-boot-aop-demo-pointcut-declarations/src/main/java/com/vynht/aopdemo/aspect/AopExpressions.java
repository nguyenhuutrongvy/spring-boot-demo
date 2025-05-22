package com.vynht.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

  @Pointcut("execution(* com.vynht.aopdemo.dao.*.*(..))")
  public void forDaoPackage() {
  }

  // Create a pointcut for getter methods
  @Pointcut("execution(* com.vynht.aopdemo.dao.*.get*(..))")
  public void forGetter() {
  }

  // Create a pointcut for setter methods
  @Pointcut("execution(* com.vynht.aopdemo.dao.*.set*(..))")
  public void forSetter() {
  }

  // Create a pointcut: include package ... exclude getter/setter
  @Pointcut("forDaoPackage() && !(forGetter() || forSetter())")
  public void forDaoPackageNoGetterSetter() {
  }
}
