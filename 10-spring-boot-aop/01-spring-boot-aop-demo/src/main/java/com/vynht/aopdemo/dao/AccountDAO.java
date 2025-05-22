package com.vynht.aopdemo.dao;

import com.vynht.aopdemo.Account;

public interface AccountDAO {

  void addAccount(Account theAccount, boolean vipFlag);

  boolean doWork();
}
