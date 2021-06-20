package com.example.mas_final_project.model.exception;

import java.text.MessageFormat;
public class LabelNotFoundException extends RuntimeException {

    public LabelNotFoundException(Long id) {
        super(MessageFormat.format("Could not find Label with id: {0}", id));
    }
}
