package com.example.mas_final_project.model.dto;

import com.example.mas_final_project.model.Performer;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PerformerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private Set<PlainArtistDto> plainArtistDtoSet = new HashSet<>();

    public static PerformerDto from(Performer performer) {
        PerformerDto performerDto = new PerformerDto();
        performerDto.setId(performer.getId());
        performerDto.setFirstName(performer.getFirstName());
        performerDto.setLastName(performer.getLastName());
        performerDto.setBio(performer.getBio());

        if (performer.getArtists() != null) {
            performerDto.setPlainArtistDtoSet(performer.getArtists()
                    .stream()
                    .map(PlainArtistDto::from)
                    .collect(Collectors.toSet()));
        }
        System.out.println(performer);
        return performerDto;
    }
}
