package com.example.mas_final_project.controller;

import com.example.mas_final_project.model.Album;
import com.example.mas_final_project.model.Artist;
import com.example.mas_final_project.model.Label;
import com.example.mas_final_project.model.Song;
import com.example.mas_final_project.model.dto.AlbumDto;
import com.example.mas_final_project.model.dto.ArtistDto;
import com.example.mas_final_project.model.dto.LabelDto;
import com.example.mas_final_project.model.dto.SongDto;
import com.example.mas_final_project.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
@CrossOrigin
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;

    }

    @PostMapping
    public ResponseEntity<ArtistDto> addArtist(@RequestBody final ArtistDto ArtistDto) {
        Artist artist = artistService.addArtist(Artist.from(ArtistDto));
        return new ResponseEntity<>(ArtistDto.from(artist), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getArtists() {
        List<Artist> Artists = artistService.getArtists();
        List<ArtistDto> ArtistsDto = Artists.stream().map(ArtistDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(ArtistsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable final Long id) {
        Artist Artist = artistService.getArtist(id);
        return new ResponseEntity<>(ArtistDto.from(Artist), HttpStatus.OK);
    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<ArtistDto> deleteArtist(@PathVariable final Long id) {
        Artist Artist = artistService.deleteArtist(id);
        return new ResponseEntity<>(ArtistDto.from(Artist), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ArtistDto> editArtist(@PathVariable final Long id, @RequestBody final ArtistDto ArtistDto) {
        Artist artist = artistService.editArtist(id, Artist.from(ArtistDto));
        return new ResponseEntity<>(ArtistDto.from(artist), HttpStatus.OK);
    }

    @PostMapping(value = "{artistId}/albums/{albumId}/add")
    public ResponseEntity<ArtistDto> addAlbumToLabel(@PathVariable final Long artistId, @PathVariable final Long albumId) {
        Artist artist = artistService.addAlbumToArtist(artistId, albumId);
        return new ResponseEntity<>(ArtistDto.from(artist), HttpStatus.OK);
    }

    @DeleteMapping(value = "{artistId}/albums/{albumId}/remove")
    public ResponseEntity<ArtistDto> removeAlbumFromLabel(@PathVariable final Long artistId, @PathVariable final Long albumId) {
        Artist artist = artistService.removeAlbumFromArtist(artistId, albumId);
        return new ResponseEntity<>(ArtistDto.from(artist), HttpStatus.OK);
    }

    @PostMapping(value = "{artistId}/performers/{performerId}/add")
    public ResponseEntity<ArtistDto> addPerformerToArtist(@PathVariable final Long artistId, @PathVariable final Long performerId) {
        Artist artist = artistService.addPerformerToArtist(artistId, performerId);
        return new ResponseEntity<>(ArtistDto.from(artist), HttpStatus.OK);
    }

    @DeleteMapping(value = "{artistId}/performers/{performerId}/remove")
    public ResponseEntity<ArtistDto> removePerformerFromArtist(@PathVariable final Long artistId, @PathVariable final Long performerId) {
        Artist artist = artistService.removePerformerFromArtist(artistId, performerId);
        return new ResponseEntity<>(ArtistDto.from(artist), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/songs")
    public ResponseEntity<List<SongDto>> getAllSongsByArtist(@PathVariable final Long id) {
        List<Song> songs = artistService.findAllSongsForArtist(id);
        List<SongDto> songsDto = songs.stream().map(SongDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(songsDto, HttpStatus.OK);
    }
}

