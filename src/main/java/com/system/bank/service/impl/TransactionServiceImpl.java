package com.system.bank.service.impl;

import com.system.bank.entity.Account;
import com.system.bank.entity.Transaction;
import com.system.bank.entity.TransactionType;
import com.system.bank.model.transaction.DepositRequestModel;
import com.system.bank.model.transaction.TransactionResponseModel;
import com.system.bank.model.transaction.WithdrawRequestModel;
import com.system.bank.repository.AccountRepository;
import com.system.bank.repository.TransactionRepository;
import com.system.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public TransactionResponseModel deposit(DepositRequestModel request) {
        Account account = accountRepository
                .findByCardNumber(request.getCardNumber())
                .orElseThrow(() -> new BadCredentialsException("Bad Credentials"));

        Long transactionId = performDeposit(account,request.getAmount());

        return new TransactionResponseModel(transactionId,request.getAmount(),account.getBalance());
    }

    @Override
    public TransactionResponseModel withdraw(WithdrawRequestModel request) {
        Account account = accountRepository
                .findByCardNumberAndCvv(request.getCardNumber(),request.getCvv())
                .orElseThrow(() -> new BadCredentialsException("Bad Credentials"));

        Long transactionId = performWithdraw(account, request.getAmount());

        return new TransactionResponseModel(transactionId,request.getAmount() ,account.getBalance());
    }
    private Long performDeposit(Account account ,double amount){
        updateAccountBalance(account,amount);
        Transaction transaction = transactionRepository.save(new Transaction(amount,account, TransactionType.DEPOSIT));
        return transaction.getId();
    }
    private Long performWithdraw(Account account,double amount){
        if(account.getBalance() < amount){
            throw new RuntimeException("Your Balance " + account.getBalance() + " is not enough to withdraw " + amount);
        }
        updateAccountBalance(account,-amount);
        Transaction transaction = transactionRepository.save(new Transaction(amount,account,TransactionType.WITHDRAW));
        return transaction.getId();
    }
    private void updateAccountBalance(Account account,double amount){
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }
}
