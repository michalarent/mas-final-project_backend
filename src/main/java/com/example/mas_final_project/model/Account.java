package com.example.mas_final_project.model;

import com.example.mas_final_project.model.dto.AccountDto;
import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Account {


    public static float fee = (float) 0.02;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float balance;

    @Nullable
    private Date lastPayoutDate;


    /**
     * Composite association with LABEL being the owner of the association
     * Label automatically creates an account
     */
    @OneToOne
    @JoinColumn(name = "fk_label")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Nullable
    private Label label;

    /**
     * Composition with ARTIST being the owner of the association
     * Label automatically creates an account
     */
    @OneToOne
    @JoinColumn(name = "fk_artist")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Nullable
    private Artist artist;

    /**
     * Private constructor
     */
    private Account(float balance, Date lastPayoutDate, Label label) {
        this.balance = balance;
        this.lastPayoutDate = lastPayoutDate;
        this.label = label;
    }

    /**
     * Private constructor (overloaded)
     */
    private Account(float balance, Date lastPayoutDate, Artist artist) {
        this.balance = balance;
        this.lastPayoutDate = lastPayoutDate;
        this.artist = artist;
    }

    /**
     * Creation method initiated in Label Service
     */
    public static Account createAccount(float balance, Date lastPayoutDate, Label label) {
        Account account = new Account(balance, lastPayoutDate, label);
        label.setAccount(account);

        return account;
    }

    /**
     * Creation method initiated in Artist Service
     */
    public static Account createAccount(float balance, Date lastPayoutDate, Artist artist) {
        Account account = new Account(balance, lastPayoutDate, artist);
        artist.setAccount(account);

        return account;
    }

    /**
     * Parsing an Account entity from a DTO
     */
    public static Account from(AccountDto accountDto) {
        Account account = new Account();
        System.out.println(accountDto);
        if (accountDto != null) {
            account.setBalance(accountDto.getBalance());
            account.setLastPayoutDate(accountDto.getLastPayoutDate());
        }
//
        return account;
    }

}
