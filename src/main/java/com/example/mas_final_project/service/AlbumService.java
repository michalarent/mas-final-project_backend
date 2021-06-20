package com.example.mas_final_project.service;

import com.example.mas_final_project.model.Album;
import com.example.mas_final_project.model.Label;
import com.example.mas_final_project.model.Song;
import com.example.mas_final_project.model.exception.AlbumNotFoundException;
import com.example.mas_final_project.model.exception.LabelNotFoundException;
import com.example.mas_final_project.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private SongService songService;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, SongService songService) {
        this.albumRepository = albumRepository;
        this.songService = songService;
    }


    public Album addAlbum(Album album) {
        return albumRepository.save(album);
    }

    public List<Album> getAlbums() {
        return StreamSupport
                .stream(albumRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Album getAlbum(Long id) {
        return albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
    }

    public Album deleteAlbum(Long id) {
        Album album = getAlbum(id);
        albumRepository.delete(album);
        return album;
    }

    @Transactional
    public Album editAlbum(Long id, Album album) {
        Album toEdit = getAlbum(id);
        toEdit.setDescription(album.getDescription());
        toEdit.setTitle(album.getTitle());
        toEdit.setReleaseDate(album.getReleaseDate());
        toEdit.setHidden(album.isHidden());
        return toEdit;
    }

    @Transactional
    public Album addSongToAlbum(Long albumId, Long songId) {
        Song song = songService.getSong(songId);
        Album album = getAlbum(albumId);
        album.addSong(song);
        song.addAlbum(album);
        return album;
    }

    @Transactional
    public Album removeSongFromAlbum(Long albumId, Long songId) {
        Song song = songService.getSong(songId);
        Album album = getAlbum(albumId);
        album.removeSong(song);
        song.removeAlbum(album);
        return album;
    }
}
