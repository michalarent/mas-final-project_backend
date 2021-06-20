package com.example.mas_final_project.model.dto;

import com.example.mas_final_project.model.Song;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SongDto {

    private Long id;
    private String title;
    private Set<PlainAlbumDto> plainAlbumDtoSet = new HashSet<>();

    public static SongDto from(Song song) {
        SongDto songDto = new SongDto();
        songDto.setId(song.getId());
        songDto.setTitle(song.getTitle());
        if (song.getAlbums() != null) {
            songDto.setPlainAlbumDtoSet(song.getAlbums().stream().map(PlainAlbumDto::from).collect(Collectors.toSet()));
        }


        return songDto;
    }
}
