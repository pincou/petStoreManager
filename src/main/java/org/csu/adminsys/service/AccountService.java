package org.csu.adminsys.service;

import org.csu.adminsys.entity.Account;

import java.util.List;

public interface AccountService {
    Account getAccount(String username);
    void insertAccount(Account account);
    public List<Account> findAllAccounts();
    public void deleteAccountById(int userid);
}
