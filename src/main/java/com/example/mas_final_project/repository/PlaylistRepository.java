package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.CreativeXEmployee;
import com.example.mas_final_project.model.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
}
