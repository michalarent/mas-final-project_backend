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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String title;

    @Nullable
    private String lyrics;

    @Nullable
    private String url;

//    @Lob
//    @Column(columnDefinition = "BLOB")
//    private byte[] songFile;

    @ManyToMany(mappedBy = "songs")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Album> albums;

    @ManyToMany(mappedBy = "playlistSongs")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Playlist> playlists;

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
