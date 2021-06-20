package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.CreativeXEmployee;
import com.example.mas_final_project.model.StandardUser;
import org.springframework.data.repository.CrudRepository;

public interface StandardUserRepository extends CrudRepository<StandardUser, Long> {
}
