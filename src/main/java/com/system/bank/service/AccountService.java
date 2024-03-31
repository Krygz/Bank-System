package com.system.bank.service;

import com.system.bank.model.account.AccountResponseModel;

import java.util.List;

public interface AccountService {
    AccountResponseModel createNewAccount();
    List<AccountResponseModel> getMyAccounts();
}
