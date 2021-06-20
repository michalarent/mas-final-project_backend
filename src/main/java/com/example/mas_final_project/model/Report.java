package com.example.mas_final_project.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PastOrPresent
    @NotNull
    private LocalDate creationDate;


}
