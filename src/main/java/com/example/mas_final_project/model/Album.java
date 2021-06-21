package com.example.mas_final_project.model;

import com.example.mas_final_project.model.dto.AlbumDto;
import com.example.mas_final_project.model.dto.LabelDto;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Blob;
import java.util.Date;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class Album {


    /**
     * Unique
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Title {max 255 chars as per limitations of VARCHAR}
     */
    @NotEmpty
    private String title;

    /**
     * Desription {max 255 chars as per limitations of VARCHAR}
     */
    @NotEmpty
    private String description;

    /**
     * Release Date - optional
     */
    @Nullable
    private Date releaseDate;


    /**
     * Upon creation of the album, it's automatically set as hidden
     */
    @Column(columnDefinition = "boolean default true")
    private boolean isHidden;

    /**
     * Currently there are problems with parsing the LOB from web form, hence its nullable
     */
    @Lob
    @Nullable
    private byte[] coverImage;


    /**
     * Artist is the owner of the association
     *
     * Nullable, because when creating an album there needs to be a timeframe when an album exists without the artist
     */
    @ManyToOne
    @JoinColumn(name = "artist_id")
    @Nullable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Artist artist;

    /**
     * Label is the owner of the association
     *
     * Nullable, because when creating an album there needs to be a timeframe when an album exists without the label
     */
    @ManyToOne
    @JoinColumn(name = "label_id")
    @Nullable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Label label;


    /**
     * Album is the owner of the association
     * Nullable, because when creating an album there needs to be a timeframe when a song exists without the Album
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "song_album",
            joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Song> songs;


    /**
     * Parsing an album entity from a DTO
     */
    public static Album from(AlbumDto albumDto) {
        Album album = new Album();
        album.setId(albumDto.getId());
        album.setTitle(albumDto.getTitle());
        album.setReleaseDate(albumDto.getReleaseDate());
        album.setDescription(albumDto.getDescription());
        album.setHidden((albumDto.isHidden()));
        album.setCoverImage(album.getCoverImage());
        return album;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }
}
