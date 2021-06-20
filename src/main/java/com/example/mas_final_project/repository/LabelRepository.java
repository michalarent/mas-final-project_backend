package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.Label;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LabelRepository extends CrudRepository<Label, Long> {

    List<Label> findAll();

}
