package com.example.mas_final_project.model.exception;

import java.text.MessageFormat;

public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException(Long id) {

        super(MessageFormat.format("Could not find Album with id: {0}", id));
    }
}
