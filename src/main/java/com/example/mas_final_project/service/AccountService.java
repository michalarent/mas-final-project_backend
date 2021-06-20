package com.example.mas_final_project.service;

import com.example.mas_final_project.model.Account;
import com.example.mas_final_project.model.Label;
import com.example.mas_final_project.model.exception.AccountNotFoundException;
import com.example.mas_final_project.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



    public List<Account> getAccounts() {
        return StreamSupport
                .stream(accountRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Transactional
    public Account editAccount(Long id, Account account) {
        Account toEdit = getAccount(id);
        toEdit.setBalance(account.getBalance());
        System.out.println("Editing account: balance = " + account.getBalance());
        toEdit.setLastPayoutDate(account.getLastPayoutDate());
        return toEdit;
    }
}
