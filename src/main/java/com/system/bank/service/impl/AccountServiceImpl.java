package com.system.bank.service.impl;

import com.system.bank.entity.Account;
import com.system.bank.entity.User;
import com.system.bank.model.account.AccountResponseModel;
import com.system.bank.repository.AccountRepository;
import com.system.bank.repository.UserRepository;
import com.system.bank.service.AccountService;
import com.system.bank.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public AccountResponseModel createNewAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Account account = new Account();
        toEntity(account,user);

        accountRepository.save(account);

        return new AccountResponseModel(account.getCardNumber(),account.getCvv(),account.getBalance());
    }

    @Override
    public List<AccountResponseModel> getMyAccounts() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return accountRepository
                .findAllByUser(user)
                .stream()
                .map(account -> new AccountResponseModel(account.getCardNumber(),account.getCvv(), account.getBalance()))
                .toList();
    }
    private void toEntity(Account account, User user){
        account.setCardNumber(generateUniqueCardNumber());
        account.setCvv(Utils.generateCvv());
        account.setUser(user);
        account.setBalance(0.0);
    }
    public String generateUniqueCardNumber(){
        String cardNumber = Utils.generateCardNumber();

        while (accountRepository.existsByCardNumber(cardNumber)){
            cardNumber = Utils.generateCardNumber();
        }
        return cardNumber;
    }
}
