package com.example.mas_final_project.controller;

import com.example.mas_final_project.model.Label;
import com.example.mas_final_project.model.dto.LabelDto;
import com.example.mas_final_project.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/labels")
@CrossOrigin
public class LabelController {

    private final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;

    }

    @PostMapping
    public ResponseEntity<LabelDto> addLabel(@RequestBody final LabelDto labelDto) {
        Label label = labelService.addLabel(Label.from(labelDto));
        return new ResponseEntity<>(LabelDto.from(label), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LabelDto>> getLabels() {
        List<Label> labels = labelService.getLabels();
        List<LabelDto> labelsDto = labels.stream().map(LabelDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(labelsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<LabelDto> getLabel(@PathVariable final Long id) {
        Label label = labelService.getLabel(id);
        return new ResponseEntity<>(LabelDto.from(label), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<LabelDto> deleteLabel(@PathVariable final Long id) {
        Label label = labelService.deleteLabel(id);
        return new ResponseEntity<>(LabelDto.from(label), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<LabelDto> editLabel(@PathVariable final Long id, @RequestBody final LabelDto labelDto) {
        Label label = labelService.editLabel(id, Label.from(labelDto));
        return new ResponseEntity<>(LabelDto.from(label), HttpStatus.OK);
    }

    @PostMapping(value = "{labelId}/albums/{albumId}/add")
    public ResponseEntity<LabelDto> addAlbumToLabel(@PathVariable final Long labelId, @PathVariable final Long albumId) {
        Label label = labelService.addAlbumToLabel(labelId, albumId);
        return new ResponseEntity<>(LabelDto.from(label), HttpStatus.OK);
    }

    @DeleteMapping(value = "{labelId}/albums/{albumId}/remove")
    public ResponseEntity<LabelDto> removeAlbumFromLabel(@PathVariable final Long labelId, @PathVariable final Long albumId) {
        Label label = labelService.removeAlbumFromLabel(labelId, albumId);

        return new ResponseEntity<>(LabelDto.from(label), HttpStatus.OK);
    }


}
