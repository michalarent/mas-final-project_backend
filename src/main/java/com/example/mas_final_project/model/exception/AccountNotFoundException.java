package com.example.mas_final_project.model.exception;

import java.text.MessageFormat;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id) {
        super(MessageFormat.format("Could not find Account with id: {0}", id));
    }
}
