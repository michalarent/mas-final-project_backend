package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.Performer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformerRepository extends CrudRepository<Performer, Long> {
}
