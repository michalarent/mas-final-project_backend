package com.example.mas_final_project.model.exception;

import java.text.MessageFormat;

public class AlbumAlreadyAssignedToArtist extends RuntimeException {

    public AlbumAlreadyAssignedToArtist(final Long albumId, final Long artistId) {
        super(MessageFormat.format("Album {0} is already assigned to artist {1}.", albumId, artistId));
    }
}
