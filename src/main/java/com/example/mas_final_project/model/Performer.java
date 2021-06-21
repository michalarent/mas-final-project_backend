package com.example.mas_final_project.model;


import com.example.mas_final_project.model.dto.PerformerDto;
import com.example.mas_final_project.model.dto.SongDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Performer {


    /**
     * Unique and auto-generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /**
     * Cannot be empty
     * Max 255 chars as per limitations of VARCHAR(255)
     */
    @NotEmpty
    private String firstName;

    /**
     * Cannot be empty
     * Max 255 chars as per limitations of VARCHAR(255)
     */
    @NotEmpty
    private String lastName;

    /**
     * Nullable
     */
    @Nullable
    private Date activityEndDate;

    /**
     * Cannot be empty
     * Max 255 chars as per limitations of VARCHAR(255)
     */
    @NotEmpty
    private String bio;

    /**
     * Many to many association
     * Max 255 chars as per limitations of VARCHAR(255)
     */
    @ManyToMany(mappedBy = "performers")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Artist> artists;

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    public void removeArtist(Artist artist) {
        artists.remove(artist);
    }

    /**
     * Parsing from a DTO
     */
    public static Performer from(PerformerDto performerDto) {
        Performer performer = new Performer();
        performer.setFirstName(performerDto.getFirstName());
        performer.setLastName(performerDto.getLastName());
        performer.setBio(performerDto.getBio());
        return performer;
    }
}
