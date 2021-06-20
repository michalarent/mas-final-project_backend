package com.example.mas_final_project.service;

import com.example.mas_final_project.model.*;
import com.example.mas_final_project.model.exception.AlbumIsAlreadyAssigned;
import com.example.mas_final_project.model.exception.ArtistNotFoundException;
import com.example.mas_final_project.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final AlbumService albumService;

    private final PerformerService performerService;

    private final EntityManager entityManager;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, AlbumService albumService, PerformerService performerService, EntityManager entityManager) {
        this.artistRepository = artistRepository;
        this.albumService = albumService;
        this.performerService = performerService;
        this.entityManager = entityManager;
    }


    public Artist addArtist(Artist artist) {
        artist.setAccount(Account.createAccount(0, null, artist));
        return artistRepository.save(artist);
    }

    public List<Artist> getArtists() {
        return StreamSupport
                .stream(artistRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Artist getArtist(Long id) {
        return artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException(id));
    }

    public Artist deleteArtist(Long id) {
        Artist Artist = getArtist(id);
        artistRepository.delete(Artist);
        return Artist;
    }

    @Transactional
    public Artist editArtist(Long id, Artist artist) {
        Artist toEdit = getArtist(id);
        toEdit.setName(artist.getName());
        toEdit.setActivityEndDate(artist.getActivityEndDate());
        return toEdit;
    }

    @Transactional
    public Artist addAlbumToArtist(Long artistId, Long albumId) {
        Artist artist = getArtist(artistId);
        Album album = albumService.getAlbum(albumId);
        System.out.println("Album: " + album);

        if (Objects.nonNull(album.getArtist())) {
            throw new AlbumIsAlreadyAssigned(albumId, album.getArtist().getId());
        }
        artist.addAlbum(album);
        album.setArtist(artist);
        return artist;
    }

    @Transactional
    public Artist removeAlbumFromArtist(Long artistId, Long albumId) {
        Artist artist = getArtist(artistId);
        Album album = albumService.getAlbum(albumId);
        if (Objects.nonNull(album.getLabel())) {
            throw new AlbumIsAlreadyAssigned(albumId, album.getArtist().getId());
        }
        artist.removeAlbum(album);
        album.setArtist(null);
        return artist;
    }

    @Transactional
    public Artist addPerformerToArtist(Long artistId, Long performerId) {
        Performer performer = performerService.getPerformer(performerId);
        Artist artist = getArtist(artistId);
        artist.addPerformer(performer);
        performer.addArtist(artist);
        return artist;
    }

    @Transactional
    public Artist removePerformerFromArtist(Long artistId, Long performerId) {
        Performer performer = performerService.getPerformer(performerId);
        Artist artist = getArtist(artistId);
        artist.removePerformer(performer);
        performer.removeArtist(artist);
        return artist;
    }

    public List<Song> findAllSongsForArtist(Long id) {
        List<Song> songs =entityManager.createQuery(
                "Select a.songs from Album a where a.artist.id ="+id).getResultList();

        return songs;
    }
}
