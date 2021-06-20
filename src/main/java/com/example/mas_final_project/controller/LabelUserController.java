package com.example.mas_final_project.controller;

import com.example.mas_final_project.model.LabelUser;
import com.example.mas_final_project.model.dto.ArtistUserDto;
import com.example.mas_final_project.service.LabelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/labels/users")
@CrossOrigin
public class LabelUserController {

    private final LabelUserService labelUserService;

    @Autowired
    public LabelUserController(LabelUserService labelUserService) {
        this.labelUserService = labelUserService;
    }


    @GetMapping
    public ResponseEntity<List<ArtistUserDto>> getLabelUsers() {
        List<LabelUser> labelUsers = labelUserService.getLabelUsers();
        labelUsers.stream().forEach(labelUser -> System.out.println(labelUser.getLabel()));
        List<ArtistUserDto> labelUsersDto = labelUsers.stream().map(ArtistUserDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(labelUsersDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ArtistUserDto> getAccount(@PathVariable final Long id) {
        LabelUser labelUser = labelUserService.getLabelUser(id);
        return new ResponseEntity<>(ArtistUserDto.from(labelUser), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ArtistUserDto> editLabelUser(@PathVariable final Long id, @RequestBody final ArtistUserDto labelUserDto) {
        LabelUser toEdit = labelUserService.editLabelUser(id, LabelUser.from(labelUserDto));
        return new ResponseEntity<>(ArtistUserDto.from(toEdit), HttpStatus.OK);
    }
}
