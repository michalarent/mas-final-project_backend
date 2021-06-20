package com.example.mas_final_project.model.dto;


import com.example.mas_final_project.model.Label;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class LabelDto {
    private Long id;
    private String name;
    private AccountDto accountDto;
    private ArtistUserDto labelUserDto;
    private PlainLabelDto plainLabelDto;
    private Date dateOfFounding;
    private Date dateOfClosure;
    private String country;
    private Set<AlbumDto> albumsDto = new HashSet<>();


    public static LabelDto from(Label label) {
        LabelDto labelDto = new LabelDto();
        labelDto.setId(label.getId());
        labelDto.setName(label.getName());
        labelDto.setDateOfFounding(label.getDateOfFounding());
        labelDto.setDateOfClosure(label.getDateOfClosure());
        labelDto.setCountry(label.getCountry());

        if (label.getAlbums() != null) {
            labelDto.setAlbumsDto(label.getAlbums()
                    .stream()
                    .map(AlbumDto::from)
                    .collect(Collectors.toSet()));
        }


        // association Label <> Account
        labelDto.setAccountDto(AccountDto.from(label.getAccount()));
        // association Label <> LabelUser
        labelDto.setLabelUserDto(ArtistUserDto.from(label.getLabelUser()));


        return labelDto;
    }

}
