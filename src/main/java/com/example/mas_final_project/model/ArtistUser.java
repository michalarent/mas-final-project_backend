package com.example.mas_final_project.model;


import com.example.mas_final_project.model.dto.ArtistUserDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class ArtistUser extends User {

    private String username;

    @OneToOne
    @JoinColumn(name = "fk_artist")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Artist artist;

    private ArtistUser(String firstName, String lastName, String username) {
        super();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.username = username;
    }

    public static ArtistUser createArtistUser(String firstName, String lastName, String username, Artist artist) {
        ArtistUser artistUser = new ArtistUser(firstName, lastName, username);
        artist.setArtistUser(artistUser);
        return artistUser;
    }

    public static ArtistUser from(ArtistUserDto artistUserDto) {
        ArtistUser artistUser = new ArtistUser();
        System.out.println(artistUser);
        if (artistUserDto != null) {
            artistUserDto.setFirstName(artistUser.getFirstName());
            artistUserDto.setLastName(artistUser.getLastName());
            artistUserDto.setUsername(artistUser.getUsername());
        }
//
        return artistUser;
    }
}
