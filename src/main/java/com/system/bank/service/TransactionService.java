package com.system.bank.service;

import com.system.bank.model.transaction.DepositRequestModel;
import com.system.bank.model.transaction.TransactionResponseModel;
import com.system.bank.model.transaction.WithdrawRequestModel;

public interface TransactionService {
    TransactionResponseModel deposit(DepositRequestModel request);
    TransactionResponseModel withdraw(WithdrawRequestModel request);
}
