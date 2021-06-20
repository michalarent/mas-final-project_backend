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
@SuperBuilder
@ToString
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @Nullable
    private Date releaseDate;

    @Column(columnDefinition = "boolean default true")
    private boolean isHidden;

    @Lob
    @Nullable
    private byte[] coverImage;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @Nullable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "label_id", nullable = true)
    @Nullable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Label label;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "song_album",
            joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Song> songs;

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
