package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.AlbumCompilation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumCompilationRepository extends CrudRepository<AlbumCompilation, Long> {
}
