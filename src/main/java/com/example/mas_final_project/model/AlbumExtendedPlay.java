package com.example.mas_final_project.model;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AlbumExtendedPlay extends Album {

    @Nullable
    private String promoUrl;
}
