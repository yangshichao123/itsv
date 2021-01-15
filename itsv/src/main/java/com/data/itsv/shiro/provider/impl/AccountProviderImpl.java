package com.data.itsv.shiro.provider.impl;


import com.data.itsv.model.vo.Account;
import com.data.itsv.service.AccountService;
import com.data.itsv.shiro.provider.AccountProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author tomsun28
 * @date 9:22 2018/2/13
 */
@Service("AccountProvider")
public class AccountProviderImpl implements AccountProvider {

      @Autowired
      @Qualifier("AccountService")
      private AccountService accountService;

    @Override
    public Account loadAccount(String appId) {
        return accountService.loadAccount(appId);
    }
}
