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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Nullable
    private Date activityEndDate;

    @NotEmpty
    private String bio;

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

    public static Performer from(PerformerDto performerDto) {
        Performer performer = new Performer();
        performer.setFirstName(performerDto.getFirstName());
        performer.setLastName(performerDto.getLastName());
        performer.setBio(performerDto.getBio());
        return performer;
    }
}
