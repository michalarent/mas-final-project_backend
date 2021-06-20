package com.example.mas_final_project.controller;

import com.example.mas_final_project.model.Album;
import com.example.mas_final_project.model.Label;
import com.example.mas_final_project.model.dto.AlbumDto;
import com.example.mas_final_project.model.dto.LabelDto;
import com.example.mas_final_project.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/albums")
@CrossOrigin
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;

    }

    @PostMapping
    public ResponseEntity<AlbumDto> addAlbum(@RequestBody final AlbumDto albumDto) {
        Album album = albumService.addAlbum(Album.from(albumDto));
        return new ResponseEntity<>(AlbumDto.from(album), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AlbumDto>> getAlbums() {
        List<Album> albums = albumService.getAlbums();
        List<AlbumDto> albumsDto = albums.stream().map(AlbumDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(albumsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable final Long id) {
        Album album = albumService.getAlbum(id);
        return new ResponseEntity<>(AlbumDto.from(album), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<AlbumDto> deleteAlbum(@PathVariable final Long id) {
        Album album = albumService.deleteAlbum(id);
        return new ResponseEntity<>(AlbumDto.from(album), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<AlbumDto> editAlbum(@PathVariable final Long id, @RequestBody final AlbumDto albumDto) {
        Album album = albumService.editAlbum(id, Album.from(albumDto));
        return new ResponseEntity<>(AlbumDto.from(album), HttpStatus.OK);
    }

    @PostMapping(value = "{albumId}/songs/{songId}/add")
    public ResponseEntity<AlbumDto> addSongToAlbum(@PathVariable final Long albumId, @PathVariable final Long songId) {
        Album album = albumService.addSongToAlbum(albumId, songId);
        return new ResponseEntity<>(AlbumDto.from(album), HttpStatus.OK);
    }

    @DeleteMapping(value = "{albumId}/songs/{songId}/remove")
    public ResponseEntity<AlbumDto> removeSongFromAlbum(@PathVariable final Long albumId, @PathVariable final Long songId) {
        Album album = albumService.removeSongFromAlbum(albumId, songId);
        return new ResponseEntity<>(AlbumDto.from(album), HttpStatus.OK);
    }
}
