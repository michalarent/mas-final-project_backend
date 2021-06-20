package com.example.mas_final_project.controller;

import com.example.mas_final_project.model.Account;
import com.example.mas_final_project.model.dto.AccountDto;
import com.example.mas_final_project.model.dto.LabelDto;
import com.example.mas_final_project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        List<Account> accounts = accountService.getAccounts();
        accounts.stream().forEach(account -> System.out.println(account.getLabel()));
        List<AccountDto> accountsDto = accounts.stream().map(AccountDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(accountsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable final Long id) {
        Account account = accountService.getAccount(id);
        return new ResponseEntity<>(AccountDto.from(account), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<AccountDto> editAccount(@PathVariable final Long id, @RequestBody final AccountDto accountDto) {
        Account toEdit = accountService.editAccount(id, Account.from(accountDto));
        return new ResponseEntity<>(AccountDto.from(toEdit), HttpStatus.OK);
    }
}
