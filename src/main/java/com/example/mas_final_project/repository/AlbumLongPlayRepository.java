package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.AlbumLongPlay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumLongPlayRepository extends CrudRepository<AlbumLongPlay, Long> {
}
