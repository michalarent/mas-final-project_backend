package com.example.mas_final_project.model.dto;

import com.example.mas_final_project.model.Album;
import com.example.mas_final_project.model.Artist;
import com.example.mas_final_project.model.Label;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class AlbumDto {
    private Long id;
    private String title;
    private String description;
    private Date releaseDate;
    private boolean isHidden;
    //    private ArtistDto artistDto;
    private PlainArtistDto artistId;
    private PlainLabelDto plainLabelDto;
    private byte[] coverImage;
    private Set<SongDto> songsDto = new HashSet<>();


    public static AlbumDto from(Album album) {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setId(album.getId());
        albumDto.setTitle(album.getTitle());
        albumDto.setDescription(album.getDescription());
        albumDto.setReleaseDate(album.getReleaseDate());
        albumDto.setHidden(album.isHidden());
        albumDto.setCoverImage(album.getCoverImage() == null ? null : album.getCoverImage());

        if (Objects.nonNull(album.getLabel())) {
            albumDto.setPlainLabelDto(PlainLabelDto.from(album.getLabel()));
        }
        if (Objects.nonNull(album.getArtist())) {
            albumDto.setArtistId(PlainArtistDto.from(album.getArtist()));
        }
        if (album.getSongs() != null) {
            albumDto.setSongsDto(album.getSongs()
                    .stream()
                    .map(SongDto::from)
                    .collect(Collectors.toSet()));
        }
        return albumDto;
    }

}
