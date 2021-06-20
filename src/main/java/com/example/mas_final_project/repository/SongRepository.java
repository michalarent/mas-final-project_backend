package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.Artist;
import com.example.mas_final_project.model.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import java.util.Optional;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

//    EntityManager entityManager = null;
//    @NamedNativeQuery(value = "Select artist_id from Album a join song_album sa on a.id =  sa.album_id join Song on sa.album_id = :id ")
//    Optional<Artist> findAllBy(Long id);

}
