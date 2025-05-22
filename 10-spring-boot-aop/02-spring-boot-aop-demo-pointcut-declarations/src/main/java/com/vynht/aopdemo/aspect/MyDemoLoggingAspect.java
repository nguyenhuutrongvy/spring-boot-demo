package com.vynht.aopdemo.aspect;

import com.vynht.aopdemo.Account;
import java.text.MessageFormat;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
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
//  @Before("execution(* com.vynht.aopdemo.dao.*.*(..))")
  @Before("com.vynht.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
  public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
    System.out.println("=====> Executing @Before advice on method");

    // Display the method signature
    MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
    System.out.println("Method: " + methodSignature);

    // Display the method arguments
    // Get args
    Object[] args = theJoinPoint.getArgs();

    // Loop through args
    for (Object tempArg : args) {
      System.out.println(tempArg);

      if (tempArg instanceof Account) {

        // Downcast and print Account specific stuff
        Account theAccount = (Account) tempArg;

        System.out.println("Account name: " + theAccount.getName());
        System.out.println("Account level: " + theAccount.getLevel());
      }
    }
  }

  // Add new advice for @AfterReturning on the findAccounts method
  @AfterReturning(
      pointcut = "execution(* com.vynht.aopdemo.dao.AccountDAO.findAccounts(..))",
      returning = "result"
  )
  public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
    // Print out which method we are advising on
    String method = theJoinPoint.getSignature().toShortString();
    System.out.println("=====> Executing @AfterReturning on method: " + method);

    // Print out the results of the method call
    System.out.println("=====> The result is: " + result);

    // Let's post-process the data
    // Convert the account names to uppercase
    convertAccountNamesToUpperCase(result);

    System.out.println("=====> The result is: " + result);
  }

  @AfterThrowing(
      pointcut = "execution(* com.vynht.aopdemo.dao.AccountDAO.findAccounts(..))",
      throwing = "theExc"
  )
  public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
    // Print out which method we are advising on
    String method = theJoinPoint.getSignature().toShortString();
    System.out.println("=====> Executing @AfterThrowing on method: " + method);

    // Log the exception
    System.out.println("=====> The exception is: " + theExc);
  }

  @After("execution(* com.vynht.aopdemo.dao.AccountDAO.findAccounts(..))")
  public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
    // Print out which method we are advising on
    String method = theJoinPoint.getSignature().toShortString();
    System.out.println("=====> Executing @After (finally) on method: " + method);
  }

  @Around("execution(* com.vynht.aopdemo.service.*.getFortune(..))")
  public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
    // Print out which method we are advising on
    String method = theProceedingJoinPoint.getSignature().toShortString();
    System.out.println("\n=====> Executing @Around on method: " + method);

    // Get begin timestamp
    long begin = System.currentTimeMillis();

    // Now, let's execute the method
    Object result = null;

    try {
      result = theProceedingJoinPoint.proceed();
    } catch (Exception e) {
      // Log the exception
      System.out.println(e.getMessage());

      // Give user a custom message
//      result = "Major accident! But no worries, your private AOP helicopter is on the way!";

      // Rethrow exception
      throw e;
    }

    // Get end timestamp
    long end = System.currentTimeMillis();

    // Compute duration and display it
    long duration = end - begin;
    System.out.println(MessageFormat.format("\n=====> Duration: {0} seconds", duration / 1000.0));

    return result;
  }

  private void convertAccountNamesToUpperCase(List<Account> result) {
    // Loop through accounts
    /*for (Account tempAccount : result) {
      // Get uppercase version of name
      String theUpperName = tempAccount.getName().toUpperCase();

      // Update the name on the account
      tempAccount.setName(theUpperName);
    }*/
    result.stream().forEach(n -> n.setName(n.getName().toUpperCase()));
  }
}
