package com.example.mas_final_project.model.dto;

import com.example.mas_final_project.model.Artist;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ArtistDto {

    private Long id;
    private String name;
    private AccountDto accountDto;
    private Date activityEndDate;
    private Set<AlbumDto> albumsDto = new HashSet<>();
    private Set<PerformerDto> performersDto = new HashSet<>();

    public static ArtistDto from(Artist artist) {

        ArtistDto artistDto = new ArtistDto();
        artistDto.setId(artist.getId());
        artistDto.setName(artist.getName());
        artistDto.setActivityEndDate(artist.getActivityEndDate());

        if (artist.getAlbums() != null) {
            artistDto.setAlbumsDto(artist.getAlbums()
                    .stream()
                    .map(AlbumDto::from)
                    .collect(Collectors.toSet()));
        }

        if (artist.getPerformers() != null) {

            artistDto.setPerformersDto(artist.getPerformers()
                    .stream()
                    .map(PerformerDto::from)
                    .collect(Collectors.toSet()));
        }
        System.out.println(artistDto.getPerformersDto());
        artistDto.setAccountDto(AccountDto.from(artist.getAccount()));
        return artistDto;

    }
}
