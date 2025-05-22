package com.vynht.aopdemo;

import com.vynht.aopdemo.dao.AccountDAO;
import com.vynht.aopdemo.dao.MembershipDAO;
import com.vynht.aopdemo.service.TrafficFortuneService;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(AopdemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
      MembershipDAO theMembershipDAO,
      TrafficFortuneService theTrafficFortuneService) {
    return runner -> {
//      demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
//      demoTheAfterReturningAdvice(theAccountDAO);
//      demoTheAfterThrowingAdvice(theAccountDAO);
//      demoTheAfterAdvice(theAccountDAO);
//      demoTheAroundAdvice(theTrafficFortuneService);
//      demoTheAroundAdviceHandleException(theTrafficFortuneService);
      demoTheAroundAdviceRethrowException(theTrafficFortuneService);
    };
  }

  private void demoTheAroundAdviceRethrowException(TrafficFortuneService theTrafficFortuneService) {
    System.out.println("\nMain Program: demoTheAroundAdviceRethrowException");
    System.out.println("Calling getFortune()");

    boolean tripWire = true;
    String data = theTrafficFortuneService.getFortune(tripWire);

    System.out.println("\nMy fortune is: " + data);
    System.out.println("Finished");
  }

  private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
    System.out.println("\nMain Program: demoTheAroundAdviceHandleException");
    System.out.println("Calling getFortune()");

    boolean tripWire = true;
    String data = theTrafficFortuneService.getFortune(tripWire);

    System.out.println("\nMy fortune is: " + data);
    System.out.println("Finished");
  }

  private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
    System.out.println("\nMain Program: demoTheAroundAdvice");
    System.out.println("Calling getFortune()");
    String data = theTrafficFortuneService.getFortune();
    System.out.println("\nMy fortune is: " + data);
    System.out.println("Finished");
  }

  private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
    // Call method to find the accounts
    List<Account> theAccounts = null;

    try {
      boolean tripWire = false;
      theAccounts = theAccountDAO.findAccounts(tripWire);
    } catch (Exception e) {
      System.out.println("\nMain Program: ... caught exception: " + e);
    }

    // Display the accounts
    System.out.println("\nMain Program: demoTheAfterThrowingAdvice");
    System.out.println("-----");
    System.out.println(theAccounts);
    System.out.println("\n");
  }

  private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
    // Call method to find the accounts
    List<Account> theAccounts = null;

    try {
      boolean tripWire = true;
      theAccounts = theAccountDAO.findAccounts(tripWire);
    } catch (Exception e) {
      System.out.println("\nMain Program: ... caught exception: " + e);
    }

    // Display the accounts
    System.out.println("\nMain Program: demoTheAfterThrowingAdvice");
    System.out.println("-----");
    System.out.println(theAccounts);
    System.out.println("\n");
  }

  private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
    // Call method to find the accounts
    List<Account> theAccounts = theAccountDAO.findAccounts();

    // Display the accounts
    System.out.println("\nMain Program: demoTheAfterReturningAdvice");
    System.out.println("-----");
    System.out.println(theAccounts);
    System.out.println("\n");
  }

  private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
    // Call the business method
    Account myAccount = new Account();
    myAccount.setName("Vy");
    myAccount.setLevel("Platinum");

    theAccountDAO.addAccount(myAccount, true);
    theAccountDAO.doWork();

    // Call the AccountDAO getter/setter methods
    theAccountDAO.setName("foobar");
    theAccountDAO.setServiceCode("silver");

    String name = theAccountDAO.getName();
    String code = theAccountDAO.getServiceCode();

    // Call the membership business method
    theMembershipDAO.addSillyMember();
    theMembershipDAO.goToSleep();

    // Do it again!
//    System.out.println("\nLet's call it again!");

    // Call the business method again
//    theAccountDAO.addAccount();
  }
}
