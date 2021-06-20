package com.example.mas_final_project.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Nullable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private StandardUser standardUser;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "song_playlist",
            joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Song> playlistSongs;

}
