package com.example.mas_final_project.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndependentArtist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String bio;

    @OneToMany(mappedBy = "independent_artist")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Nullable
    private Set<Song> portfolio;

    @ManyToOne
    @JoinColumn(name = "creative_x_employee")
    @Nullable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CreativeXEmployee creativeXEmployee;

}
