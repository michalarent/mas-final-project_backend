package com.example.mas_final_project.model.exception;

import java.text.MessageFormat;

public class AlbumIsAlreadyAssigned extends RuntimeException {

    public AlbumIsAlreadyAssigned(final Long albumId, final Long labelId) {
        super(MessageFormat.format("Album {0} is already assigned to label {1}.", albumId, labelId));
    }

}
