package com.vynht.aopdemo.dao;

import com.vynht.aopdemo.Account;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {

  private String name;
  private String serviceCode;

  @Override
  public List<Account> findAccounts() {
    return findAccounts(false);
  }

  @Override
  public List<Account> findAccounts(boolean tripWire) {
    // Simulate an exception
    if (tripWire) {
      throw new RuntimeException("No soup for you!!!");
    }

    List<Account> myAccounts = new ArrayList<>();

    // Create sample accounts
    Account temp1 = new Account("Thanh", "Silver");
    Account temp2 = new Account("Vy", "Platinum");
    Account temp3 = new Account("Kiet", "Gold");

    // Add them to our accounts list
    myAccounts.add(temp1);
    myAccounts.add(temp2);
    myAccounts.add(temp3);

    return myAccounts;
  }

  @Override
  public void addAccount(Account theAccount, boolean vipFlag) {
    System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT\n");
  }

  @Override
  public boolean doWork() {
    System.out.println(getClass() + ": doWork()\n");
    return false;
  }

  public String getName() {
    System.out.println(getClass() + ": in getName()\n");
    return name;
  }

  public void setName(String name) {
    System.out.println(getClass() + ": in setName()\n");
    this.name = name;
  }

  public String getServiceCode() {
    System.out.println(getClass() + ": in getServiceCode()\n");
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    System.out.println(getClass() + ": in setServiceCode()\n");
    this.serviceCode = serviceCode;
  }
}
