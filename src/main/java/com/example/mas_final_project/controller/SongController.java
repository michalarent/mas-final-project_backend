package com.example.mas_final_project.controller;

import com.example.mas_final_project.model.Album;
import com.example.mas_final_project.model.Artist;
import com.example.mas_final_project.model.Song;
import com.example.mas_final_project.model.dto.AlbumDto;
import com.example.mas_final_project.model.dto.ArtistDto;
import com.example.mas_final_project.model.dto.SongDto;
import com.example.mas_final_project.service.AlbumService;
import com.example.mas_final_project.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/songs")
@CrossOrigin
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity<SongDto> addSong(@RequestBody final SongDto songDto) {
        Song song = songService.addSong(Song.from(songDto));
        return new ResponseEntity<>(SongDto.from(song), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SongDto>> getSongs() {
        List<Song> songs = songService.getSongs();
        List<SongDto> songsDto = songs.stream().map(SongDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(songsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SongDto> getSong(@PathVariable final Long id) {
        Song song = songService.getSong(id);
        return new ResponseEntity<>(SongDto.from(song), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<SongDto> deleteSong(@PathVariable final Long id) {
        Song song = songService.deleteSong(id);
        return new ResponseEntity<>(SongDto.from(song), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<SongDto> editAlbum(@PathVariable final Long id, @RequestBody final SongDto songDto) {
        Song song = songService.editSong(id, Song.from(songDto));
        return new ResponseEntity<>(SongDto.from(song), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/artist")
    public ResponseEntity<ArtistDto> findArtistForSong(@PathVariable final Long id) {
        Artist artist = songService.findArtistsForSong(id);
        ArtistDto artistDto = ArtistDto.from(artist);
        return new ResponseEntity<>(artistDto, HttpStatus.OK);
    }
}
