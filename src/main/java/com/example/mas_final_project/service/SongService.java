package com.example.mas_final_project.service;

import com.example.mas_final_project.model.Artist;
import com.example.mas_final_project.model.Song;
import com.example.mas_final_project.model.exception.AlbumNotFoundException;
import com.example.mas_final_project.model.exception.ArtistNotFoundException;
import com.example.mas_final_project.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SongService {

    private final SongRepository songRepository;

    @PersistenceContext
    private final EntityManager entityManager;


    @Autowired
    public SongService(SongRepository songRepository, EntityManager entityManager) {
        this.songRepository = songRepository;
        this.entityManager = entityManager;
    }

    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    public List<Song> getSongs() {
        return StreamSupport
                .stream(songRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Song getSong(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
    }

    public Song deleteSong(Long id) {
        Song song = getSong(id);
        songRepository.delete(song);
        return song;
    }

    public Song editSong(Long id, Song song) {
        Song toEdit = getSong(id);
        toEdit.setTitle(song.getTitle());
        return toEdit;
    }

    public Artist findArtistsForSong(Long id) {
        Artist artist = entityManager.createQuery(
                "select distinct a.artist from Album a " +
                        "join a.songs song join song.albums album " +
                        "where song.id = " + id, Artist.class).getSingleResult();

        return artist;
    }
}
