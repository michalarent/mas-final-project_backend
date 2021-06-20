package com.example.mas_final_project.model;


import com.example.mas_final_project.model.dto.ArtistUserDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class LabelUser extends User {

    private String username;

    @OneToOne
    @JoinColumn(name = "fk_labelUser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Label label;

    private LabelUser(String firstName, String lastName, String username) {
        super();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.username = username;
    }

    public static LabelUser createLabelUser(String firstName, String lastName, String username, Label label) {
        LabelUser labelUser = new LabelUser(firstName, lastName, username);
        label.setLabelUser(labelUser);
        return labelUser;
    }

    public static LabelUser from(ArtistUserDto labelUserDto) {
        LabelUser labelUser = new LabelUser();
        System.out.println(labelUser);
        if (labelUserDto != null) {
            labelUser.setFirstName(labelUserDto.getFirstName());
            labelUser.setLastName(labelUserDto.getLastName());
            labelUser.setUsername(labelUserDto.getUsername());
        }
//
        return labelUser;
    }
}
