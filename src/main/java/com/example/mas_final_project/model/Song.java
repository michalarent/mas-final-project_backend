package com.example.mas_final_project.model;

import com.example.mas_final_project.model.dto.SongDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Song {

    /**
     * Unique and auto-generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Title of the song
     * Cannot be empty
     * Max 255 characters as per limitations of VARCHAR(255)
     */
    @NotEmpty
    private String title;

    /**
     * Lyrics of the song
     * Nullable
     * Max 255 characters as per limitations of VARCHAR(255)
     */
    @Nullable
    private String lyrics;

    /**
     * URL of the song
     * Nullable
     * Max 255 characters as per limitations of VARCHAR(255)
     */
    @Nullable
    private String url;

//    @Lob
//    @Column(columnDefinition = "BLOB")
//    private byte[] songFile;


    /**
     * Many to many association with Album
     * Album is an owner of the association - when deleting an album, a song gets deleted too
     */
    @ManyToMany(mappedBy = "songs")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Album> albums;

    /**
     * Many to many association with Playlist
     * A song won't get deleted upon deletion of a playlist
     */
    @ManyToMany(mappedBy = "playlistSongs")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Playlist> playlists;

    /**
     * Listen history - association with an attribute
     */
    @OneToMany(mappedBy = "song")
    private Set<ListenHistory> listenHistory = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "independent_artist")
    @Nullable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private IndependentArtist independent_artist;

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }

    public static Song from(SongDto songDto) {
        Song song = new Song();
        song.setTitle(songDto.getTitle());

        return song;
    }

}
