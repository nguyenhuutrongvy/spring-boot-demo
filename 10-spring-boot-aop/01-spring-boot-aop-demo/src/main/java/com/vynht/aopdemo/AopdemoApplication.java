package com.vynht.aopdemo;

import com.vynht.aopdemo.dao.AccountDAO;
import com.vynht.aopdemo.dao.MembershipDAO;
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
      MembershipDAO theMembershipDAO) {
    return runner -> {
      demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
    };
  }

  private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
    // Call the business method
    Account myAccount = new Account();
    theAccountDAO.addAccount(myAccount, true);
    theAccountDAO.doWork();

    // Call the membership business method
    theMembershipDAO.addSillyMember();
    theMembershipDAO.goToSleep();

    // Do it again!
//    System.out.println("\nLet's call it again!");

    // Call the business method again
//    theAccountDAO.addAccount();
  }
}
