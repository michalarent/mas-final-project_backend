package com.example.mas_final_project.model.dto;

import com.example.mas_final_project.model.Label;
import lombok.Data;

import java.util.Date;

@Data
public class PlainLabelDto {

    private Long id;
    private String name;
    private Date dateOfFounding;
    private Date dateOfClosure;
    private String country;

    public static PlainLabelDto from(Label label) {
        PlainLabelDto labelDto = new PlainLabelDto();
        labelDto.setId(label.getId());
        labelDto.setName(label.getName());
        labelDto.setDateOfFounding(label.getDateOfFounding());
        labelDto.setDateOfClosure(label.getDateOfClosure());
        System.out.println("Account: " + label.getAccount());
        labelDto.setCountry(label.getCountry());
        return labelDto;
    }

}
