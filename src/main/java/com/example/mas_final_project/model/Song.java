package com.example.mas_final_project.model;

import com.example.mas_final_project.model.dto.SongDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
