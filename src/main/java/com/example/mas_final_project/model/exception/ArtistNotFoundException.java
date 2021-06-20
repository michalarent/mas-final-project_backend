package com.example.mas_final_project.model.exception;

import java.text.MessageFormat;

public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException(Long id) {

        super(MessageFormat.format("Could not find Artist with id: {0}", id));
    }
}