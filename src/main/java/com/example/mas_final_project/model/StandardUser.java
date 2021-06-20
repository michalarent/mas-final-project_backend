package com.example.mas_final_project.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
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
    @NotEmpty
    private String email;

    @Column(unique = true)
    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private LocalDate birthDate;

    @OneToMany(mappedBy = "standardUser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Nullable
    private Set<Playlist> playlists;

    @OneToMany(mappedBy = "standardUser")
    private Set<ListenHistory> listenHistory = new HashSet<>();


}
