package org.csu.adminsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.adminsys.Mappers.AccountMapper;
import org.csu.adminsys.entity.Account;
import org.csu.adminsys.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Account getAccount(String username) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",username);
        return accountMapper.selectOne(queryWrapper);
    }
    @Override
    public void insertAccount(Account account) {
        accountMapper.insert(account);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountMapper.selectList(null);
    }
    @Override
    public void deleteAccountById(int userid) {
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        Account account=accountMapper.selectOne(queryWrapper);
        accountMapper.delete(queryWrapper);
    }
}
