package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.Label;
import com.example.mas_final_project.model.LabelUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelUserRepository extends CrudRepository<LabelUser, Long> {
}
