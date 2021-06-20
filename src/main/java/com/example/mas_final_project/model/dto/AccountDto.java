package com.example.mas_final_project.model.dto;

import com.example.mas_final_project.model.Account;
import com.example.mas_final_project.model.Label;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AccountDto {

    private Long id;
    private float balance;
    private Date lastPayoutDate;

    public static AccountDto from(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setBalance(account.getBalance());
        accountDto.setLastPayoutDate(account.getLastPayoutDate());
        return accountDto;
    }

}
