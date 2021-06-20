package com.example.mas_final_project.model;

import com.example.mas_final_project.model.dto.AlbumDto;
import com.example.mas_final_project.model.dto.LabelDto;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Label {

    public static float royaltiesPerListen = (float) 0.005;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String name;

    @NotEmpty
    private Date dateOfFounding;

    @Nullable
    private Date dateOfClosure;

    @NotEmpty
    private String country;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_labelUser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private LabelUser labelUser;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_label")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Account account;

    @OneToMany(mappedBy = "label")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Nullable
    private Set<Album> albums;


    public Label(String name, Date dateOfFounding, Date dateOfClosure, String country) {
        System.out.println("CREATING label");
        this.name = name;
        this.dateOfFounding = dateOfFounding;
        this.dateOfClosure = dateOfClosure;
        this.country = country;
        this.labelUser = new LabelUser();
        Account.createAccount(0, Date.from(Instant.now()), this);
        LabelUser.createLabelUser("John", "Malkovich", "jmalkovich", this);
    }


    public static Label from(LabelDto labelDto) {
        Label label = new Label();
        label.setId(labelDto.getId());
        label.setName(labelDto.getName());
        label.setDateOfFounding(labelDto.getDateOfFounding());
        label.setDateOfClosure(labelDto.getDateOfClosure());
        label.setCountry(labelDto.getCountry());

        return label;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }
}
