package com.example.mas_final_project.model.dto;

import com.example.mas_final_project.model.Album;
import lombok.Data;

@Data
public class PlainAlbumDto {

    private Long id;
    private String title;
    private String description;

    public static PlainAlbumDto from(Album album) {
        PlainAlbumDto plainAlbumDto = new PlainAlbumDto();
        plainAlbumDto.setId(album.getId());
        plainAlbumDto.setTitle(album.getTitle());
        plainAlbumDto.setDescription(album.getDescription());
        return plainAlbumDto;
    }
}
