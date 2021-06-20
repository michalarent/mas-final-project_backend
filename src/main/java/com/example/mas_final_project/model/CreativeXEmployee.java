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
public class CreativeXEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @OneToMany(mappedBy = "creativeXEmployee")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Nullable
    private Set<IndependentArtist> independentArtists;

}
