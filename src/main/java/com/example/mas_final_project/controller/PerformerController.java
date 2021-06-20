package com.example.mas_final_project.controller;

import com.example.mas_final_project.model.Performer;
import com.example.mas_final_project.model.dto.PerformerDto;
import com.example.mas_final_project.service.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/performers")
@CrossOrigin
public class PerformerController {

    private final PerformerService performerService;

    @Autowired
    public PerformerController(PerformerService performerService) {
        this.performerService = performerService;
    }

    @PostMapping
    public ResponseEntity<PerformerDto> addPerformer(@RequestBody final PerformerDto performerDto) {
        Performer performer = performerService.addPerformer(Performer.from(performerDto));
        return new ResponseEntity<>(PerformerDto.from(performer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PerformerDto>> getPerformers() {
        List<Performer> performers = performerService.getPerformers();
        List<PerformerDto> performersDto = performers.stream().map(PerformerDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(performersDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PerformerDto> getPerformer(@PathVariable final Long id) {
        Performer performer = performerService.getPerformer(id);
        return new ResponseEntity<>(PerformerDto.from(performer), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<PerformerDto> deletePerformer(@PathVariable final Long id) {
        Performer performer = performerService.deletePerformer(id);
        return new ResponseEntity<>(PerformerDto.from(performer), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<PerformerDto> editAlbum(@PathVariable final Long id, @RequestBody final PerformerDto performerDto) {
        Performer performer = performerService.editPerformer(id, Performer.from(performerDto));
        return new ResponseEntity<>(PerformerDto.from(performer), HttpStatus.OK);
    }
}
