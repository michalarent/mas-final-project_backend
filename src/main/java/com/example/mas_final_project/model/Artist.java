package com.example.mas_final_project.model;

import com.example.mas_final_project.model.dto.ArtistDto;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Artist {

    public static final float royaltiesPerListen = (float) 0.0001;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Nullable
    private Date activityEndDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_artist")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Account account;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_artistuser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ArtistUser artistUser;

    @OneToMany(mappedBy = "artist")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Nullable
    private Set<Album> albums;

    public Artist(String name) {
        this.name = name;

    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "performer_artist",
            joinColumns = @JoinColumn(name = "performer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Performer> performers;


    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }

    public void addPerformer(Performer performer) {
        performers.add(performer);
    }

    public void removePerformer(Performer performer) {
        performers.remove(performer);
    }

    public static Artist from(ArtistDto artistDto) {
        Artist artist = new Artist();
        artist.setId(artistDto.getId());
        artist.setName(artistDto.getName());
        artist.setActivityEndDate(artistDto.getActivityEndDate());
        return artist;
    }

}
