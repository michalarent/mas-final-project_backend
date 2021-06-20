package com.example.mas_final_project.model.dto;

import com.example.mas_final_project.model.Artist;
import com.example.mas_final_project.model.Label;
import lombok.Data;

import java.util.Date;

@Data
public class PlainArtistDto {

    private Long artistId;
    private String name;

    public static PlainArtistDto from(Artist artist) {
        PlainArtistDto artistDto = new PlainArtistDto();
        artistDto.setArtistId(artist.getId());
        artistDto.setName(artist.getName());

        return artistDto;
    }
}
