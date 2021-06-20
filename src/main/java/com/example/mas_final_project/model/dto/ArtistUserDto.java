package com.example.mas_final_project.model.dto;

import com.example.mas_final_project.model.LabelUser;
import lombok.Data;

@Data
public class ArtistUserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;

    public static ArtistUserDto from(LabelUser labelUser) {
        ArtistUserDto labelUserDto = new ArtistUserDto();
        labelUserDto.setId(labelUser.getId());
        labelUserDto.setFirstName(labelUser.getFirstName());
        labelUserDto.setLastName(labelUser.getLastName());
        labelUserDto.setUsername(labelUser.getUsername());
        return labelUserDto;
    }

}
