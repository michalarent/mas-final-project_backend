package com.example.mas_final_project.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardUser extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "standardUser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Nullable
    private Set<Playlist> playlists;


}
